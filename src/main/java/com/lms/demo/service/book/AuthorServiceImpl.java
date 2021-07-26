package com.lms.demo.service.book;

import com.lms.demo.dao.model.Author;
import com.lms.demo.dao.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    private AuthorRepository authorRepository;


    // modify this later
    @Override
    public Author getAuthorByName(String authorName) {
        return authorRepository.findByName(authorName).get();
    }

    @Override
    public Author saveAuthor(String authorName) {
        Optional<Author> authorFound = authorRepository.findByName(authorName);
        log.info("Author found: {}", authorFound);

        if(authorFound.isPresent()) {
            return authorFound.get();
        } else {
            log.info("Creating new Author");
            Author newAuthor = new Author();
            newAuthor.setName(authorName);
            return authorRepository.save(newAuthor);
        }
    }
}
