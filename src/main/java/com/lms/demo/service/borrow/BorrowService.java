package com.lms.demo.service.borrow;

import com.lms.demo.dao.model.BorrowDetails;

public interface BorrowService {
    BorrowDetails saveBorrow(BorrowDetails borrowDetails);
}
