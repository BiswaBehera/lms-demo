package com.lms.demo.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lms.demo.error.ErrorResponseMessages;
import com.lms.demo.error.IllegalPropertyValueException;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.util.Objects;

@Data
public class BookBorrowDto {

    @JsonProperty("library_id")
    @NotNull(message = "Library Id is required")
    private Long id;

    @JsonProperty("isbn_code")
    @NotNull(message = "ISBN code is required")
    private Long isbnCode;
}
