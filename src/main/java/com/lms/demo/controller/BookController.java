package com.lms.demo.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.lms.demo.dao.model.Book;
import com.lms.demo.dto.book.AddBookDto;
import com.lms.demo.dto.book.AddBookResponse;
import com.lms.demo.error.*;
import com.lms.demo.service.book.BookService;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/book/add")
    public AddBookResponse addBook(@RequestBody AddBookDto addBookDto) throws IllegalPropertyValueException, NotAnAdminException, DuplicateEntityException, EntityNotFoundException {

        if(addBookDto.getTitle() == null) {
            throw new IllegalPropertyValueException(ErrorResponseMessages.nullTitleValueForBook);
        }

        if(addBookDto.getAdminId() == null) {
            throw new IllegalPropertyValueException(ErrorResponseMessages.nullAdminValueForBook);
        }

        if(addBookDto.getAuthorName() == null) {
            throw new IllegalPropertyValueException(ErrorResponseMessages.nullAuthorValueForBook);
        }

        Integer n = addBookDto.getNumberOfCopies();
        if(n == null || n < 1 || n > 5) {
            throw new IllegalPropertyValueException(ErrorResponseMessages.nullCopiesValueForBook);
        }

        return bookService.saveBook(addBookDto);
    }
}
