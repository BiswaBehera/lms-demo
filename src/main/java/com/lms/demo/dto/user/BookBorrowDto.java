package com.lms.demo.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BookBorrowDto {

    @JsonProperty("library_id")
    private Long id;

    @JsonProperty("isbn_code")
    private Long isbnCode;
}
