package com.lms.demo.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lms.demo.error.ErrorResponseMessages;
import com.lms.demo.error.IllegalPropertyValueException;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
public class ReturnBookDto {

    @JsonProperty("issue_id")
    @NotNull(message = "Issue ID cannot be null")
    private Long issueId;

    @JsonProperty("library_id")
    @NotNull(message = "Library ID cannot be null")
    private Long libraryId;

    @JsonProperty("barcode")
    @NotNull(message = "Barcode cannot be null")
    private Long barcode;
}
