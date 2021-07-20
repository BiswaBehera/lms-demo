package com.lms.demo.dao.repository;

import com.lms.demo.dao.model.Author;
import com.lms.demo.dao.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleAndAuthor(String title, Author author);
}
