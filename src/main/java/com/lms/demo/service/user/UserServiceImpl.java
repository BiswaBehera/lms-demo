package com.lms.demo.service.user;

import com.lms.demo.dao.model.BookItem;
import com.lms.demo.dao.model.BorrowDetails;
import com.lms.demo.dao.model.User;
import com.lms.demo.dao.repository.UserRepository;
import com.lms.demo.dto.mapper.BorrowDetailsMapper;
import com.lms.demo.dto.mapper.UserMapper;
import com.lms.demo.dto.user.AddUserDto;
import com.lms.demo.dto.user.AddUserResponse;
import com.lms.demo.dto.user.BookBorrowDto;
import com.lms.demo.dto.user.BookBorrowResponse;
import com.lms.demo.error.*;
import com.lms.demo.service.book.BookItemService;
import com.lms.demo.service.book.BookService;
import com.lms.demo.service.borrow.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookItemService bookItemService;
    @Autowired
    private BorrowService borrowService;

    @Override
    public AddUserResponse saveUser(AddUserDto addUserDto) throws IllegalPropertyValueException, DuplicateEntityException {
        UserMapper userMapper = new UserMapper();

        //duplicate name check
        if(userRepository.findByName(addUserDto.getName()) != null) {
            throw new DuplicateEntityException(ErrorResponseMessages.duplicateNameForUser);
        }
        //name validation
        if(addUserDto.getName().length() < 3) {
            throw new IllegalPropertyValueException(ErrorResponseMessages.illegalNameValueForUser);
        }

        //duplicate contact number check
        if(userRepository.findByContactNumber(addUserDto.getContactNumber()) != null) {
            throw new DuplicateEntityException(ErrorResponseMessages.duplicateContactForUser);
        }
        //contact number validation
        if(addUserDto.getContactNumber().length() != 10) {
            throw new IllegalPropertyValueException(ErrorResponseMessages.illegalContactValueForUser);
        }

        User user = userMapper.fromAddUser(addUserDto);

        AddUserResponse response = new AddUserResponse(userRepository.save(user));
        response.setMessage("user successfully created");
        return response;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public BookBorrowResponse saveBorrowBook(BookBorrowDto bookBorrowDto) throws EntityNotFoundException, CopiesNotAvailableException {

        //id check
        if(getUserById(bookBorrowDto.getId()).isEmpty()) {
            throw new EntityNotFoundException(ErrorResponseMessages.userNotFound);
        }
        //book check
        if(bookService.fetchBookById(bookBorrowDto.getIsbnCode()).isEmpty()) {
            throw new EntityNotFoundException(ErrorResponseMessages.bookNotFound);
        }

        //book availability
        List<BookItem> copies = bookItemService.fetchByIsbnAndAvailability(bookBorrowDto.getIsbnCode(), true);
        if(copies.size() == 0) {
            throw new CopiesNotAvailableException(ErrorResponseMessages.copiesNotAvailable);
        }

        BookItem bookItem = copies.get(0);
        bookItemService.updateAvailable(bookItem.getBarcode());
        User user = getUserById(bookBorrowDto.getId()).get();

        BorrowDetailsMapper borrowDetailsMapper = new BorrowDetailsMapper();
        BorrowDetails borrowDetails = borrowDetailsMapper.fromBookBorrowDto(bookBorrowDto);
        borrowDetails.setUser(user);
        borrowDetails.setBookItem(bookItem);

        return new BookBorrowResponse(borrowService.saveBorrow(borrowDetails));
    }
}
