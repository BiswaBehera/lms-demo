package com.lms.demo.controller;

import com.lms.demo.dto.user.*;
import com.lms.demo.error.*;
import com.lms.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<AddUserResponse> addUser(
            @Valid @RequestBody final AddUserDto addUserDto
    ) throws IllegalPropertyValueException, DuplicateEntityException{

        AddUserResponse addUserResponse = userService.saveUser(addUserDto);

        return new ResponseEntity<AddUserResponse>(addUserResponse, HttpStatus.CREATED);
    }

    @PostMapping("/user/borrow")
    public ResponseEntity<BookBorrowResponse> borrowBook(
            @Valid @RequestBody final BookBorrowDto bookBorrowDto
    ) throws IllegalPropertyValueException, CopiesNotAvailableException, EntityNotFoundException {

        BookBorrowResponse bookBorrowResponse = userService.saveBorrowBook(bookBorrowDto);

        return new ResponseEntity<BookBorrowResponse>(bookBorrowResponse, HttpStatus.CREATED);
    }

    @PutMapping("/user/return")
    public ResponseEntity<ReturnBookResponse> returnBook(
            @Valid @RequestBody final ReturnBookDto returnBookDto
    ) throws IllegalPropertyValueException, EntityNotFoundException, InvalidEntityException, BookAlreadyReturnedException {

        ReturnBookResponse returnBookResponse = userService.returnBook(returnBookDto);

        return new ResponseEntity<ReturnBookResponse>(returnBookResponse, HttpStatus.ACCEPTED);
    }

    @PostMapping("/user/get_library_card")
    public LibraryCardResponse fetchLibraryCard(@RequestBody GetLibraryCardDto getLibraryCardDto) throws IllegalPropertyValueException, EntityNotFoundException {

        getLibraryCardDto.nullCheckForRequiredProperties();

        return userService.fetchLibraryCard(getLibraryCardDto);
    }
}
