package com.lms.demo.controller;

import com.lms.demo.dao.model.Book;
import com.lms.demo.dto.book.AddBookDto;
import com.lms.demo.dto.book.AddBookResponse;
import com.lms.demo.dto.book.SearchType;
import com.lms.demo.error.*;
import com.lms.demo.service.book.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/book")
    public AddBookResponse addBook(@RequestBody AddBookDto addBookDto) throws IllegalPropertyValueException, NotAnAdminException, DuplicateEntityException, EntityNotFoundException {

        log.info("entered addBook()");
        log.info("DTO received: {}", addBookDto);

        //checking if all the required values are available or not
        addBookDto.nullCheckForRequiredProperties();
        log.info("null check passed");

        //saving the book
        return bookService.saveBook(addBookDto);
    }

    @GetMapping("/book/{search_by}/{search_string}")
    //search_by is a enum with all the category values
    //search_string is the string to search with
    public List<Book> searchBook(@PathVariable("search_by") SearchType searchType,
                                  @PathVariable("search_string") String searchString) {
        return bookService.searchBook(searchType, searchString);
    }
}
