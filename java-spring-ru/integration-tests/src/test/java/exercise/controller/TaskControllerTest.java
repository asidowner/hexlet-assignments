package exercise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.model.Task;
import exercise.repository.TaskRepository;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    private Task generateTask() {
        return Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
                .create();
    }

    // BEGIN
    @Test
    public void testShow() throws Exception {
        var task = generateTask();
        taskRepository.save(task);

        var result = mockMvc.perform(get("/tasks/" + task.getId()))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isObject()
                .containsEntry("title", task.getTitle())
                .containsEntry("description", task.getDescription());
    }

    @Test
    public void testShowNegative() throws Exception {
        var id = 99999;
        var result = mockMvc.perform(get("/tasks/" + id))
                .andExpect(status().isNotFound())
                .andReturn();

        var body = result.getResponse().getContentAsString();

        assertThat(body).isEqualTo("Task with id " + id + " not found");
    }

    @Test
    public void testDelete() throws Exception {
        var task = generateTask();
        taskRepository.save(task);

        mockMvc.perform(delete("/tasks/" + task.getId()))
                .andExpect(status().isOk());

        var maybeDeletedTask = taskRepository.findById(task.getId());

        assertThat(maybeDeletedTask.isEmpty()).isTrue();
    }

    @Test
    public void testCreate() throws Exception {
        var task = generateTask();

        var request = post("/tasks").contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(task));

        var result = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();

        var body = result.getResponse().getContentAsString();

        assertThatJson(body).isObject()
                .containsEntry("title", task.getTitle())
                .containsEntry("description", task.getDescription());
    }

    @Test
    public void testUpdate() throws Exception {
        var initTask = generateTask();
        var taskWithChange = generateTask();

        taskRepository.save(initTask);

        var request = put("/tasks/" + initTask.getId()).contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(taskWithChange));

        var result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();

        assertThatJson(body).isObject()
                .containsEntry("title", taskWithChange.getTitle())
                .containsEntry("description", taskWithChange.getDescription())
                .doesNotContainEntry("title", initTask.getTitle())
                .doesNotContainEntry("description", initTask.getDescription());

        var actualTask = taskRepository.findById(initTask.getId());

        assertThat(actualTask.isPresent()).isTrue();
        assertThat(actualTask.get().getTitle()).isEqualTo(taskWithChange.getTitle());
        assertThat(actualTask.get().getDescription()).isEqualTo(taskWithChange.getDescription());
    }
    // END
}
