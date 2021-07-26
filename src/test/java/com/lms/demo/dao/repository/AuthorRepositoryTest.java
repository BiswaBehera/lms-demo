package com.lms.demo.dao.repository;

import com.lms.demo.dao.model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        Author author = new Author();
        author.setName("Morris");

        testEntityManager.persist(author);
    }

    @Test
    @DisplayName("Author is found by Id")
    public void whenValidAuthorId_thenAuthorFound() {
        Optional<Author> found = authorRepository.findById(7L);

        found.ifPresent(author -> assertEquals(author.getName(), "Morris"));
    }
}