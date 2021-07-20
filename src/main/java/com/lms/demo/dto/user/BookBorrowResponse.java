package com.lms.demo.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lms.demo.dao.model.BookItem;
import com.lms.demo.dao.model.BorrowDetails;
import lombok.Data;
import java.sql.Date;

@Data
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
