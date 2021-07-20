package com.lms.demo.dto.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddBookDto {
    @JsonProperty("admin_id")
    private Long adminId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("genre")
    private String genre;

    @JsonProperty("number_of_copies")
    private Integer numberOfCopies;

    @JsonProperty("author_name")
    private String authorName;
}
