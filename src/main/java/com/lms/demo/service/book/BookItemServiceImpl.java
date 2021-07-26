package com.lms.demo.service.book;

import com.lms.demo.dao.model.Book;
import com.lms.demo.dao.model.BookItem;
import com.lms.demo.dao.repository.BookItemRepository;
import com.lms.demo.dao.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class BookItemServiceImpl implements BookItemService{

    @Autowired
    private BookItemRepository bookItemRepository;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public void saveBookItems(Book book, Integer numberOfCopies) {
        //number of copies to be added to the library
        log.info("creating book items for new Book");
        if(Objects.isNull(numberOfCopies)) {
            log.info("Default Number of Copies assigned");
            numberOfCopies = 5;
        }

        for(int i = 0; i < numberOfCopies; i++) {

            log.info("creating copy-{}", i+1);
            BookItem bookItem = new BookItem();
            bookItem.setBook(book);
            bookItem.setAvailable(true);

            bookItemRepository.save(bookItem);
        }
    }

    @Override
    public List<BookItem> fetchByIsbnAndAvailability(Long isbn, Boolean availability) {
        return bookItemRepository.findByBookAndAvailable(bookRepository.getById(isbn), availability);
    }

    @Override
    @Transactional
    public void updateAvailable(Long barcode, Boolean available) {
        BookItem bookItem = bookItemRepository.getById(barcode);
        bookItem.setAvailable(available);
    }
}