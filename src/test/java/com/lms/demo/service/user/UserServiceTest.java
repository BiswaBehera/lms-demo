package com.lms.demo.service.user;

import com.lms.demo.dao.model.*;
import com.lms.demo.dao.repository.UserRepository;
import com.lms.demo.dto.user.AddUserDto;
import com.lms.demo.dto.user.AddUserResponse;
import com.lms.demo.dto.user.BookBorrowDto;
import com.lms.demo.dto.user.BookBorrowResponse;
import com.lms.demo.error.*;
import com.lms.demo.service.book.BookItemService;
import com.lms.demo.service.book.BookService;
import com.lms.demo.service.borrow.BorrowService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private BookService bookService;
    @Mock
    private BookItemService bookItemService;
    @Mock
    private BorrowService borrowService;

    @InjectMocks
    private final UserService userService = new UserServiceImpl();

    private AddUserDto addUserDto;
    private User user;
    private BorrowDetails borrowDetails;
    private BookBorrowDto bookBorrowDto;
    private BookBorrowResponse bookBorrowResponse;
    private Book book;
    private BookItem bookItem;
    private Author author;

    @BeforeEach
    void setup() {

        addUserDto = new AddUserDto();
        addUserDto.setName("biswa");
        addUserDto.setContactNumber("1234567891");
        addUserDto.setPassword("biswa");
        addUserDto.setIsAdmin(false);

        user = new User();
        user.setId(1L);
        user.setName("biswa");
        user.setPassword("biswa");
        user.setContactNumber("1234567891");
        user.setIsAdmin(false);

        bookBorrowDto = new BookBorrowDto();
        bookBorrowDto.setId(1L);
        bookBorrowDto.setIsbnCode(1L);

        author = new Author();
        author.setId(1L);
        author.setName("morris");

        book = new Book();
        book.setIsbnCode(1L);
        book.setTitle("Spring");
        book.setGenre("study");
        book.setAuthor(author);

        bookItem = new BookItem();
        bookItem.setBarcode(1L);
        bookItem.setBook(book);
        bookItem.setAvailable(true);

        borrowDetails = new BorrowDetails();
        borrowDetails.setId(1L);
        borrowDetails.setUser(user);
        borrowDetails.setBookItem(bookItem);
        borrowDetails.setCheckoutDate(new Date(System.currentTimeMillis()));
        borrowDetails.setDueDate(new Date(System.currentTimeMillis()));
        borrowDetails.setFine(0);
        borrowDetails.setPaid(false);

//        bookBorrowResponse = new BookBorrowResponse();
//        bookBorrowResponse.setDueDate(new Date(System.currentTimeMillis()));
//        bookBorrowResponse.setBarcode(2L);
//        bookBorrowResponse.setMessage("Book successfully borrowed");
    }

    //------------------------------------------- Tests For saveUser() -------------------------------------------

    @Test
    @DisplayName("All valid data given. User created successfully")
    public void saveUser_test_case1() throws Exception{
        //this should return null, since name has to be unique
        when(userRepository.findByName(anyString())).thenReturn(null);

        //this should return null, since contact number has to be unique
        when(userRepository.findByContactNumber(anyString())).thenReturn(null);

        //mocking the save method in repository
        when(userRepository.save(any(User.class))).thenReturn(user);

        AddUserResponse addUserResponse = userService.saveUser(addUserDto);

        assertEquals("user successfully created", addUserResponse.getMessage());
    }

    @Test
    @DisplayName("When user name already exists for another user")
    public void saveUser_test_case2() {
        when(userRepository.findByName(anyString())).thenReturn(user);

        DuplicateEntityException e = assertThrows(DuplicateEntityException.class,
                () -> userService.saveUser(addUserDto));

        assertEquals(ErrorResponseMessages.duplicateNameForUser, e.getMessage());
    }

    @Test
    @DisplayName("When user name is too small, less than 3 characters")
    public void saveUser_test_case3() {
        addUserDto.setName("bi");
        when(userRepository.findByName(anyString())).thenReturn(null);

        IllegalPropertyValueException e = assertThrows(IllegalPropertyValueException.class,
                () -> userService.saveUser(addUserDto));

        assertEquals(ErrorResponseMessages.illegalNameValueForUser, e.getMessage());
    }

    @Test
    @DisplayName("When contact number already exists for another user")
    public void saveUser_test_case4() {
        when(userRepository.findByName(anyString())).thenReturn(null);
        when(userRepository.findByContactNumber(anyString())).thenReturn(user);

        DuplicateEntityException e = assertThrows(DuplicateEntityException.class,
                () -> userService.saveUser(addUserDto));

        assertEquals(ErrorResponseMessages.duplicateContactForUser, e.getMessage());
    }

    @Test
    @DisplayName("When contact number is invalid. length != 10")
    public void saveUser_test_case5() {
        addUserDto.setContactNumber("12345");
        when(userRepository.findByName(anyString())).thenReturn(null);
        when(userRepository.findByContactNumber(anyString())).thenReturn(null);

        IllegalPropertyValueException e = assertThrows(IllegalPropertyValueException.class,
                () -> userService.saveUser(addUserDto));

        assertEquals(ErrorResponseMessages.illegalContactValueForUser, e.getMessage());
    }

    //-------------------------------------------  Test for getUserByid() -------------------------------------------

    @Test
    @DisplayName("When all data are valid, user found successfully")
    public void getUserById_test_case1() throws EntityNotFoundException {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        User found = userService.getUserById(1L);

        assertEquals(addUserDto.getName(), found.getName());
    }

    @Test
    @DisplayName("When library id given doesn't exist")
    public void getUserById_test_case2() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        EntityNotFoundException e = assertThrows(EntityNotFoundException.class,
                () -> userService.getUserById(1L));

        assertEquals(ErrorResponseMessages.userNotFound, e.getMessage());
    }

    //--------------------------------------------- saveBorrowBook() ----------------------------------

    @Test
    @DisplayName("All valid data. borrow successfully")
    public void saveBorrowBook_test_case1() throws EntityNotFoundException, CopiesNotAvailableException {
        List<BookItem> listFound = new ArrayList<>();
        listFound.add(bookItem);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(bookService.fetchBookById(anyLong())).thenReturn(Optional.of(book));
        when(bookItemService.fetchByIsbnAndAvailability(anyLong(), anyBoolean())).thenReturn(listFound);
        when(borrowService.saveBorrow(any(BorrowDetails.class))).thenReturn(borrowDetails);

        BookBorrowResponse bookBorrowResponse = userService.saveBorrowBook(bookBorrowDto);

        assertEquals("Book successfully borrowed", bookBorrowResponse.getMessage());
    }

    @Test
    @DisplayName("Wrong Library Id")
    public void saveBorrowBook_test_case2()  {

        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        EntityNotFoundException e = assertThrows(EntityNotFoundException.class,
                () -> userService.saveBorrowBook(bookBorrowDto));

        assertEquals(ErrorResponseMessages.userNotFound, e.getMessage());
    }

    @Test
    @DisplayName("Wrong Isbn code")
    public void saveBorrowBook_test_case3() {

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(bookService.fetchBookById(anyLong())).thenReturn(Optional.empty());

        EntityNotFoundException e = assertThrows(EntityNotFoundException.class,
                () -> userService.saveBorrowBook(bookBorrowDto));

        assertEquals(ErrorResponseMessages.bookNotFound, e.getMessage());
    }

}