package com.lms.demo.dto.mapper;

import com.lms.demo.dao.model.Author;
import com.lms.demo.dao.model.Book;
import com.lms.demo.dto.book.AddBookDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookMapper {
    public Book fromAddBook(AddBookDto addBookDto, Author author) {
        log.info("Creating Book Entity from addBookDto");

        Book book = new Book();
        book.setTitle(addBookDto.getTitle());
        book.setGenre(addBookDto.getGenre());
        book.setAuthor(author);

        return book;
    }
}
