package com.lms.demo.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lms.demo.dao.model.BorrowDetails;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class BookBorrowResponse {

    @JsonProperty("barcode")
    private Long barcode;

    @JsonProperty("due_date")
    private Date dueDate;

    @JsonProperty("message")
    private String message;

    public BookBorrowResponse(BorrowDetails borrowDetails) {
        this.setBarcode(borrowDetails.getBookItem().getBarcode());
        this.setDueDate(borrowDetails.getDueDate());
        this.setMessage("Book successfully borrowed");
    }
}
