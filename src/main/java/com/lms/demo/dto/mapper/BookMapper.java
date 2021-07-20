package com.lms.demo.dto.mapper;

import com.lms.demo.dao.model.Book;
import com.lms.demo.dto.book.AddBookDto;

public class BookMapper {
    public Book fromAddBook(AddBookDto addBookDto) {
        Book book = new Book();
        book.setTitle(addBookDto.getTitle());
        book.setGenre(addBookDto.getGenre());

        return book;
    }
}
