package com.lms.demo.controller;

import com.lms.demo.dto.user.*;
import com.lms.demo.error.*;
import com.lms.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public AddUserResponse addUser(@RequestBody AddUserDto addUserDto) throws IllegalPropertyValueException, DuplicateEntityException{

        //null check for required properties
        addUserDto.nullCheckForRequiredProperties();

        return userService.saveUser(addUserDto);
    }

    @PostMapping("/user/borrow")
    public BookBorrowResponse borrowBook(@RequestBody BookBorrowDto bookBorrowDto) throws IllegalPropertyValueException, CopiesNotAvailableException, EntityNotFoundException {

        bookBorrowDto.nullCheckForRequiredProperties();

        return userService.saveBorrowBook(bookBorrowDto);
    }

    @PutMapping("/user/return")
    public ReturnBookResponse returnBook(@RequestBody ReturnBookDto returnBookDto) throws IllegalPropertyValueException, EntityNotFoundException, InvalidEntityException, BookAlreadyReturnedException {

        returnBookDto.nullCheckForRequiredProperties();

        return userService.returnBook(returnBookDto);
    }

    @PostMapping("/user/get_library_card")
    public LibraryCardResponse fetchLibraryCard(@RequestBody GetLibraryCardDto getLibraryCardDto) throws IllegalPropertyValueException, EntityNotFoundException {

        getLibraryCardDto.nullCheckForRequiredProperties();

        return userService.fetchLibraryCard(getLibraryCardDto);
    }
}
