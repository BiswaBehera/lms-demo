package com.lms.demo.controller;

import com.lms.demo.dao.model.Book;
import com.lms.demo.dto.book.AddBookDto;
import com.lms.demo.dto.book.AddBookResponse;
import com.lms.demo.dto.book.SearchType;
import com.lms.demo.error.*;
import com.lms.demo.service.book.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<AddBookResponse> addBook(
            @Valid @RequestBody final AddBookDto addBookDto
    ) throws IllegalPropertyValueException, NotAnAdminException, DuplicateEntityException, EntityNotFoundException {

        log.info("entered addBook()");
        log.info("DTO received: {}", addBookDto);

        //saving the book
        AddBookResponse addBookResponse = bookService.saveBook(addBookDto);

        return new ResponseEntity<AddBookResponse>(addBookResponse, HttpStatus.CREATED);
    }

    @GetMapping("/book/{search_by}/{search_string}")
    //search_by is a enum with all the category values
    //search_string is the string to search with
    public ResponseEntity<List<Book>> searchBook(
            @PathVariable("search_by") final SearchType searchType,
            @PathVariable("search_string") final String searchString) {

        List<Book> listOfBooks = bookService.searchBook(searchType, searchString);

        return new ResponseEntity<List<Book>>(listOfBooks, HttpStatus.FOUND);
    }
}
