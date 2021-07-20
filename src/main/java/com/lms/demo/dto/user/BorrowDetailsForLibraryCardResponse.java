package com.lms.demo.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lms.demo.dao.model.BorrowDetails;
import lombok.Data;
import java.sql.Date;

@Data
public class BorrowDetailsForLibraryCardResponse {
    @JsonProperty("issue_id")
    private Long id;

    @JsonProperty("isbn_code")
    private Long isbnCode;

    @JsonProperty("book_title")
    private String bookTitle;

    @JsonProperty("barcode")
    private Long barcode;

    @JsonProperty("due_date")
    private Date dueDate;

    @JsonProperty("checkout_date")
    private Date checkoutDate;

    @JsonProperty("fine")
    private Integer fine;

    @JsonProperty("paid")
    private boolean paid;

    public BorrowDetailsForLibraryCardResponse(BorrowDetails borrowDetails) {
        this.setIsbnCode(borrowDetails.getBookItem().getBook().getIsbnCode());
        this.setBookTitle(borrowDetails.getBookItem().getBook().getTitle());
        this.setBarcode(borrowDetails.getBookItem().getBarcode());
        this.setCheckoutDate(borrowDetails.getCheckoutDate());
        this.setDueDate(borrowDetails.getDueDate());
        this.setFine(borrowDetails.getFine());
        this.setPaid(borrowDetails.getPaid());
        this.setId(borrowDetails.getId());
    }
}
