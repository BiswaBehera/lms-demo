package com.lms.demo.service.book;

import com.lms.demo.dao.model.Book;
import com.lms.demo.dto.book.AddBookDto;
import com.lms.demo.dto.book.AddBookResponse;
import com.lms.demo.dto.book.SearchType;
import com.lms.demo.error.DuplicateEntityException;
import com.lms.demo.error.EntityNotFoundException;
import com.lms.demo.error.NotAnAdminException;

import java.util.List;
import java.util.Optional;

public interface BookService {
    AddBookResponse saveBook(AddBookDto addBookDto) throws NotAnAdminException, DuplicateEntityException, EntityNotFoundException;
    Optional<Book> fetchBookById(Long isbn);

    List<Book> searchBookRoot(SearchType searchType, String searchString);
    List<Book> searchBookByTitle(String searchString);
    List<Book> searchBookByGenre(String searchString);
    List<Book> searchBookByAuthor(String searchString);
}
