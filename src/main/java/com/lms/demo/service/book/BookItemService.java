package com.lms.demo.service.book;

import com.lms.demo.dao.model.Book;
import com.lms.demo.dao.model.BookItem;

import java.util.List;

public interface BookItemService {
    void saveBookItems(Book book, Integer numberOfCopies);
    List<BookItem> fetchByIsbnAndAvailability(Long isbn, Boolean availability);
    void updateAvailable(Long barcode, Boolean available);
}
