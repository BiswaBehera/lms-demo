package com.lms.demo.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lms.demo.error.ErrorResponseMessages;
import com.lms.demo.error.IllegalPropertyValueException;
import lombok.Data;

import java.util.Objects;

@Data
public class ReturnBookDto {

    @JsonProperty("issue_id")
    private Long issueId;

    @JsonProperty("library_id")
    private Long libraryId;

    @JsonProperty("barcode")
    private Long barcode;

    public void nullCheckForRequiredProperties() throws IllegalPropertyValueException {
        //null issue id check
        if(Objects.isNull(this.getIssueId())) {
            throw new IllegalPropertyValueException(ErrorResponseMessages.nullIssueIdForReturn);
        }
        //null library id check
        if(Objects.isNull(this.getLibraryId())) {
            throw new IllegalPropertyValueException(ErrorResponseMessages.nullLibraryIdForReturn);
        }
        //null barcode check
        if(Objects.isNull(this.getBarcode())) {
            throw new IllegalPropertyValueException(ErrorResponseMessages.nullBarcodeForReturn);
        }
    }
}
