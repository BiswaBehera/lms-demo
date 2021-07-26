package com.lms.demo.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lms.demo.error.ErrorResponseMessages;
import com.lms.demo.error.IllegalPropertyValueException;
import lombok.Data;

import java.util.Objects;

@Data
public class BookBorrowDto {

    @JsonProperty("library_id")
    private Long id;

    @JsonProperty("isbn_code")
    private Long isbnCode;

    public void nullCheckForRequiredProperties() throws IllegalPropertyValueException {
        // null check for library id
        if(Objects.isNull(this.getId())) {
            throw new IllegalPropertyValueException(ErrorResponseMessages.nullIdValueForBorrow);
        }

        //null check for isbn code
        if(Objects.isNull(this.getIsbnCode())) {
            throw new IllegalPropertyValueException(ErrorResponseMessages.nullIsbnValueForBorrow);
        }
    }
}
