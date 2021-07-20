package com.lms.demo.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lms.demo.dao.model.BorrowDetails;
import lombok.Data;

@Data
public class ReturnBookResponse {

    @JsonProperty("issue_id")
    private Long issueId;

    @JsonProperty("message")
    private String message;

    @JsonProperty("fine")
    private Integer fine;

    public ReturnBookResponse(BorrowDetails borrowDetails) {
        this.setIssueId(borrowDetails.getId());
        this.setFine(borrowDetails.getFine());
        this.setMessage("Book Successfully returned");
    }
}
