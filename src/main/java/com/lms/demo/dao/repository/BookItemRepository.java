package com.lms.demo.dao.repository;

import com.lms.demo.dao.model.Book;
import com.lms.demo.dao.model.BookItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookItemRepository extends JpaRepository<BookItem, Long> {
    List<BookItem> findByBookAndAvailable(Book book, Boolean available);

    @Modifying
    @Query("UPDATE BookItem bi SET bi.available = :available WHERE bi.barcode = :barcode")
    int updateAvailable(@Param("barcode") Long barcode, @Param("available") Boolean available);
}
