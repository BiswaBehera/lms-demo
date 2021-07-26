package com.lms.demo.service.book;

import com.lms.demo.dao.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
class AuthorServiceTest {

    @Mock
    private AuthorService authorService;

    @MockBean
    private AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
    }
}