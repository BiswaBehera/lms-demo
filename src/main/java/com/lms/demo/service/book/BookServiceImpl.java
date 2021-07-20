package com.lms.demo.service.book;

import com.lms.demo.dao.model.Author;
import com.lms.demo.dao.model.Book;
import com.lms.demo.dao.model.BookItem;
import com.lms.demo.dao.model.User;
import com.lms.demo.dao.repository.BookRepository;
import com.lms.demo.dto.book.AddBookDto;
import com.lms.demo.dto.book.AddBookResponse;
import com.lms.demo.dto.mapper.BookMapper;
import com.lms.demo.error.DuplicateEntityException;
import com.lms.demo.error.EntityNotFoundException;
import com.lms.demo.error.ErrorResponseMessages;
import com.lms.demo.error.NotAnAdminException;
import com.lms.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


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
        //admin check
        if(!user.getIsAdmin()) {
            throw new NotAnAdminException(ErrorResponseMessages.notAnAdminForAddBook);
        }

        //create author if not there
        String authorName = addBookDto.getAuthorName();
        Author author = new Author();
        if(authorService.getAuthorByName(authorName) != null) {
            author = authorService.getAuthorByName(authorName);
        } else {
            author.setName(authorName);
            author = authorService.saveAuthor(author);
        }

        //duplicate book check
        if(bookRepository.findByTitleAndAuthor(addBookDto.getTitle(), author).size() != 0) {
            throw new DuplicateEntityException(ErrorResponseMessages.duplicateBooK);
        }

        BookMapper bookMapper = new BookMapper();
        Book book = bookMapper.fromAddBook(addBookDto);
        book.setAuthor(author);

        book = bookRepository.save(book);

        for(int i = 0; i < addBookDto.getNumberOfCopies(); i++) {
            BookItem bookItem = new BookItem();
            bookItem.setAvailable(true);
            bookItem.setBook(book);
            bookItemService.saveBookItem(bookItem);
        }

        return new AddBookResponse(book);
    }

    @Override
    public Optional<Book> fetchBookById(Long isbn) {
        return bookRepository.findById(isbn);
    }
}
