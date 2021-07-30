package com.lms.demo.dto.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Slf4j
@Data
public class AddBookDto {

    @JsonProperty("admin_id")
    @NotNull(message = "Admin ID is required")
    private Long adminId;

    @JsonProperty("title")
    @NotNull(message = "Title of the book is Required")
    @NotBlank(message = "Title of the book is Required")
    private String title;

    @JsonProperty("genre")
    private String genre;

    @JsonProperty("number_of_copies")
    private Integer numberOfCopies;

    @JsonProperty("author_name")
    @NotNull(message = "Author name is required")
    @NotBlank(message = "Author name is required")
    private String authorName;
}
