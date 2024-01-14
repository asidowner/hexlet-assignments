package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private static final String AUTHOR_WITH_ID_S_NOT_FOUND = "Author with id %s not found";
    // BEGIN
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    public List<AuthorDTO> getAll() {
        var authors = authorRepository.findAll();
        return authors.stream()
                .map(authorMapper::map)
                .toList();
    }

    public AuthorDTO create(AuthorCreateDTO data) {
        var author = authorMapper.map(data);
        authorRepository.save(author);
        return authorMapper.map(author);
    }

    public AuthorDTO findById(Long id) {
        var author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(AUTHOR_WITH_ID_S_NOT_FOUND, id)));

        return authorMapper.map(author);
    }

    public AuthorDTO update(Long id, AuthorUpdateDTO data) {
        var author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(AUTHOR_WITH_ID_S_NOT_FOUND, id)));

        authorMapper.update(data, author);
        authorRepository.save(author);

        return authorMapper.map(author);
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
    // END
}
