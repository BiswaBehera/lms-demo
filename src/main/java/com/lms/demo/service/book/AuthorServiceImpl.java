package com.lms.demo.service.book;

import com.lms.demo.dao.model.Author;
import com.lms.demo.dao.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author getAuthorByName(String authorName) {
        return authorRepository.findByName(authorName);
    }

    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }
}
