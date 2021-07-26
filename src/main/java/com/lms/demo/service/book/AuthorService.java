package com.lms.demo.service.book;

import com.lms.demo.dao.model.Author;

public interface AuthorService {
    Author getAuthorByName(String authorName);
    Author saveAuthor(String authorName);
}
