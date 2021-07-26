package com.lms.demo.controller;

import com.lms.demo.dto.user.AddUserDto;
import com.lms.demo.dto.user.AddUserResponse;
import com.lms.demo.dto.user.BookBorrowDto;
import com.lms.demo.dto.user.BookBorrowResponse;
import com.lms.demo.error.EntityNotFoundException;
import com.lms.demo.error.ErrorResponseMessages;
import com.lms.demo.error.IllegalPropertyValueException;
import com.lms.demo.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private AddUserResponse outputUser;
    private BookBorrowResponse bookBorrowResponse;

    @BeforeEach
    void setup() {
        outputUser = new AddUserResponse();
        outputUser.setId(1L);
        outputUser.setName("eren");
        outputUser.setContactNumber("1234567891");
        outputUser.setMessage("user successfully created");

        bookBorrowResponse = new BookBorrowResponse();
        bookBorrowResponse.setDueDate(new Date(System.currentTimeMillis()));
        bookBorrowResponse.setBarcode(1L);
        bookBorrowResponse.setMessage("book borrowed");
    }

    @Test
    void addUser() throws Exception {
        AddUserDto inputUser = new AddUserDto();
        inputUser.setName("eren");
        inputUser.setContactNumber("1234567891");
        inputUser.setIsAdmin(false);
        inputUser.setPassword("eren");

        Mockito.when(userService.saveUser(Mockito.any(AddUserDto.class))).thenReturn(outputUser);

        mockMvc.perform(post("/user")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\n" +
                "    \"name\": \"eren\",\n" +
                "    \"contact_number\": \"1234567891\",\n" +
                "    \"password\": \"eren\",\n" +
                "    \"is_admin\": false\n" +
                "}")).andExpect(status().isOk());
    }

    @Test
    void borrowBook() throws Exception {
        BookBorrowDto bookBorrowDto = new BookBorrowDto();
        bookBorrowDto.setId(1L);
        bookBorrowDto.setIsbnCode(1L);

        Mockito.when(userService.saveBorrowBook(bookBorrowDto)).thenReturn(bookBorrowResponse);

        mockMvc.perform(post("/user/borrow")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\n" +
                "    \"library_id\": 1,\n" +
                "    \"isbn_code\": 1\n" +
                "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.barcode")
                        .value(bookBorrowResponse.getBarcode()));
    }

    @Test
    void borrowBookException() throws Exception {
        BookBorrowDto bookBorrowDto = new BookBorrowDto();
        bookBorrowDto.setId(2L);
        bookBorrowDto.setIsbnCode(1L);

        EntityNotFoundException e = new EntityNotFoundException(ErrorResponseMessages.bookNotFound);

        Mockito.when(userService.saveBorrowBook(bookBorrowDto)).thenThrow(e);

        mockMvc.perform(post("/user/borrow")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"library_id\": 2\n" +
                        "    \"isbn_code\": 1\n" +
                        "}"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message")
                        .value(ErrorResponseMessages.bookNotFound));
    }
}