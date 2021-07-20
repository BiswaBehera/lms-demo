package com.lms.demo.dao.repository;

import com.lms.demo.dao.model.Book;
import com.lms.demo.dao.model.BookItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookItemRepository extends JpaRepository<BookItem, Long> {
    List<BookItem> findByBookAndAvailable(Book book, Boolean available);

    @Modifying
    @Query("UPDATE book_item b SET b.available = !b.available WHERE b.barcode = ?")
    BookItem updateAvailable(Long barcode);
}
