package com.lms.demo.service.user;

import com.lms.demo.dao.model.User;
import com.lms.demo.dto.user.*;
import com.lms.demo.error.CopiesNotAvailableException;
import com.lms.demo.error.DuplicateEntityException;
import com.lms.demo.error.IllegalPropertyValueException;
import com.lms.demo.error.EntityNotFoundException;

import java.util.Optional;

public interface UserService {

    AddUserResponse saveUser(AddUserDto addUserDto) throws IllegalPropertyValueException, DuplicateEntityException;
    Optional<User> getUserById(Long id);
    BookBorrowResponse saveBorrowBook(BookBorrowDto bookBorrowDto) throws EntityNotFoundException, CopiesNotAvailableException;
    LibraryCardResponse fetchLibraryCard(GetLibraryCardDto getLibraryCardDto) throws EntityNotFoundException;
}
