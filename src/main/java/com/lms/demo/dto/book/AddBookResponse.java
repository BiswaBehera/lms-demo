package com.lms.demo.dto.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lms.demo.dao.model.Book;
import lombok.Data;

@Data
public class AddBookResponse {
    @JsonProperty("title")
    private String title;

    @JsonProperty("author_name")
    private String authorName;

    @JsonProperty("genre")
    private String genre;

    @JsonProperty("message")
    private String message;

    public AddBookResponse(Book book) {
        this.setTitle(book.getTitle());
        this.setAuthorName(book.getAuthor().getName());
        this.setGenre(book.getGenre());
        this.setMessage("Book successfully added. Book items also created");
    }
}
