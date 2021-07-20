package com.lms.demo.dto.mapper;

import com.lms.demo.dao.model.BorrowDetails;
import com.lms.demo.dto.user.BookBorrowDto;

import java.sql.Date;
import java.util.Calendar;

public class BorrowDetailsMapper {
    public BorrowDetails fromBookBorrowDto(BookBorrowDto bookBorrowDto) {
        BorrowDetails borrowDetails = new BorrowDetails();

        // borrow date
        long millisecond = System.currentTimeMillis();
        borrowDetails.setCheckoutDate(new Date(millisecond));

        // due date = borrow date + 10
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(millisecond));
        c.add(Calendar.DATE, 10);
        borrowDetails.setDueDate(new Date(c.getTimeInMillis()));

        borrowDetails.setFine(0);
        borrowDetails.setPaid(false);

        return borrowDetails;
    }
}
