package com.lms.demo.service.user;

import com.lms.demo.dao.model.User;
import com.lms.demo.dto.user.*;
import com.lms.demo.error.*;

import java.util.Optional;

public interface UserService {

    AddUserResponse saveUser(AddUserDto addUserDto) throws IllegalPropertyValueException, DuplicateEntityException;
    User getUserById(Long id) throws EntityNotFoundException;
    BookBorrowResponse saveBorrowBook(BookBorrowDto bookBorrowDto) throws EntityNotFoundException, CopiesNotAvailableException;
    LibraryCardResponse fetchLibraryCard(GetLibraryCardDto getLibraryCardDto) throws EntityNotFoundException;
    ReturnBookResponse returnBook(ReturnBookDto returnBookDto) throws EntityNotFoundException, InvalidEntityException;
}
