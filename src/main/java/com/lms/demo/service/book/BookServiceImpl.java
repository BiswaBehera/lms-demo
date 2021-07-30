package com.lms.demo.service.book;

import com.lms.demo.dao.model.Author;
import com.lms.demo.dao.model.Book;
import com.lms.demo.dao.model.BookItem;
import com.lms.demo.dao.model.User;
import com.lms.demo.dao.repository.BookRepository;
import com.lms.demo.dto.book.AddBookDto;
import com.lms.demo.dto.book.AddBookResponse;
import com.lms.demo.dto.book.SearchType;
import com.lms.demo.dto.mapper.BookMapper;
import com.lms.demo.error.DuplicateEntityException;
import com.lms.demo.error.EntityNotFoundException;
import com.lms.demo.error.ErrorResponseMessages;
import com.lms.demo.error.NotAnAdminException;
import com.lms.demo.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BookItemService bookItemService;
    @Autowired
    private AuthorService authorService;

    @Override
    public AddBookResponse saveBook(AddBookDto addBookDto) throws NotAnAdminException, DuplicateEntityException, EntityNotFoundException {

        //user exist check
        User user = userService.getUserById(addBookDto.getAdminId());
        log.info("User found");

        //admin check
        if(!user.getIsAdmin()) {
            log.warn("User is not an Admin");
            throw new NotAnAdminException(ErrorResponseMessages.notAnAdminForAddBook);
        }
        log.info("User is an Admin");

        //create author if not there
        Author author = authorService.saveAuthor(addBookDto.getAuthorName());

        //duplicate book check
        if(bookRepository.findByTitleAndAuthor(addBookDto.getTitle(), author).size() != 0) {
            log.warn("Book Already exists");
            throw new DuplicateEntityException(ErrorResponseMessages.duplicateBooK);
        }

        //map book from addBookDto and save it
        BookMapper bookMapper = new BookMapper();
        Book book = bookRepository.save(bookMapper.fromAddBook(addBookDto, author));

        //add copies to the library
        bookItemService.saveBookItems(book, addBookDto.getNumberOfCopies());

        return new AddBookResponse(book);
    }

    @Override
    public Optional<Book> fetchBookById(Long isbn) {
        return bookRepository.findById(isbn);
    }

    @Override
    public List<Book> searchBook(SearchType searchType, String searchString) {

        switch (searchType) {
            case AUTHOR: return searchBookByAuthor(searchString);
            case TITLE: return searchBookByTitle(searchString);
            case GENRE: return searchBookByGenre(searchString);
            case ALL: return fetchAllBooks();
            default: return new ArrayList<>();
        }
    }

    @Override
    public  List<Book> fetchAllBooks() {
        return  bookRepository.findAll();
    }

    @Override
    public List<Book> searchBookByTitle(String searchString) {
        return bookRepository.findByTitleContaining(searchString);
    }

    @Override
    public List<Book> searchBookByGenre(String searchString) {
        return bookRepository.findByGenreContaining(searchString);
    }

    @Override
    public List<Book> searchBookByAuthor(String searchString) {
        return bookRepository.findByAuthorNameContaining(searchString);
    }
}
