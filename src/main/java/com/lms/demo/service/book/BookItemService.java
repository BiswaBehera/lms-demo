package com.lms.demo.service.book;

import com.lms.demo.dao.model.BookItem;

import java.util.List;

public interface BookItemService {
    BookItem saveBookItem(BookItem bookItem);
    List<BookItem> fetchByIsbnAndAvailability(Long isbn, Boolean availability);
    int updateAvailable(Long barcode, Boolean available);
}
