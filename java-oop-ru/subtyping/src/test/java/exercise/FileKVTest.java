package exercise;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
// BEGIN
import static org.assertj.core.api.Assertions.assertThat;
// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.CREATE);
    }

    // BEGIN
    @Test
    public void fileKVTest() {
        Map<String, String> testMap = Map.of("key1", "value1", "key2", "value2");
        KeyValueStorage storage = new FileKV(filepath, testMap);
        assertThat(storage.toMap()).isEqualTo(testMap);

        assertThat(storage.get("key1", "valueDefault")).isEqualTo("value1");
        assertThat(storage.get("key3", "valueDefault")).isEqualTo("valueDefault");

        storage.unset("key2");
        assertThat(storage.toMap()).isEqualTo(Map.of("key1", "value1"));

        storage.set("key3", "value3");
        assertThat(storage.toMap()).isEqualTo(Map.of("key1", "value1", "key3", "value3"));

        KeyValueStorage emptyStorage = new FileKV(filepath, new HashMap<>());
        assertThat(emptyStorage.toMap()).isEqualTo(new HashMap<>());
    }

    @Test
    public void immutableTest() {
        Map<String, String> initial = new HashMap<>();
        initial.put("key", "10");

        Map<String, String> clonedInitial = new HashMap<>();
        clonedInitial.putAll(initial);

        KeyValueStorage storage = new FileKV(filepath, initial);

        initial.put("key2", "value2");
        assertThat(storage.toMap()).isEqualTo(clonedInitial);

        Map<String, String> map = storage.toMap();
        map.put("key2", "value2");
        assertThat(storage.toMap()).isEqualTo(clonedInitial);
    }
    // END
}
