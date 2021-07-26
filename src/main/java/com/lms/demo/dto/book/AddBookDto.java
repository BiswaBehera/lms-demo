package com.lms.demo.dto.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lms.demo.error.ErrorResponseMessages;
import com.lms.demo.error.IllegalPropertyValueException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
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

    public void nullCheckForRequiredProperties() throws IllegalPropertyValueException {
        if(Objects.isNull(this.getTitle())) {
            log.warn("Title value is null");

            throw new IllegalPropertyValueException(ErrorResponseMessages.nullTitleValueForBook);
        }

        if(Objects.isNull(this.getAdminId())) {
            log.warn("Admin ID value is null");

            throw new IllegalPropertyValueException(ErrorResponseMessages.nullAdminValueForBook);
        }

        if(Objects.isNull(this.getAuthorName())) {
            log.warn("Author Name value is null");

            throw new IllegalPropertyValueException(ErrorResponseMessages.nullAuthorValueForBook);
        }
    }
}
