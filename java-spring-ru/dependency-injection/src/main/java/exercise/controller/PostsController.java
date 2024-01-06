package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {
    private static final String NOT_FOUND_ERROR_MESSAGE = "Post with id %s not found";

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping()
    public List<Post> index() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post show(@PathVariable long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND_ERROR_MESSAGE, id)));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @PutMapping("/{id}")
    public Post update(@PathVariable long id, @RequestBody Post data) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND_ERROR_MESSAGE, id)));

        if (!data.getBody().isEmpty()) {
            post.setBody(data.getBody());
        }

        if (!data.getTitle().isEmpty()) {
            post.setTitle(data.getTitle());
        }

        return postRepository.save(post);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        checkPostExistsById(id);
        commentRepository.deleteByPostId(id);
        postRepository.deleteById(id);
    }

    private void checkPostExistsById(long id) {
        if (!postRepository.existsById(id)) {
            String errorMessage = String.format(NOT_FOUND_ERROR_MESSAGE, id);
            throw new ResourceNotFoundException(errorMessage);
        }
    }
}
// END
