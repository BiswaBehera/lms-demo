package com.lms.demo.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReturnBookDto {

    @JsonProperty("issue_id")
    private Long issueId;

    @JsonProperty("library_id")
    private Long libraryId;

    @JsonProperty("barcode")
    private Long barcode;
}
