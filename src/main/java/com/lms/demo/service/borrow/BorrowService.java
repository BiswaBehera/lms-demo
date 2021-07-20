package com.lms.demo.service.borrow;

import com.lms.demo.dao.model.BorrowDetails;
import com.lms.demo.error.EntityNotFoundException;

import java.util.List;

public interface BorrowService {
    BorrowDetails saveBorrow(BorrowDetails borrowDetails);
    BorrowDetails fetchByIssueId(Long id) throws EntityNotFoundException;
    BorrowDetails updateFineWithoutPersist(BorrowDetails borrowDetails);
    List<BorrowDetails> fetchActiveBorrowsByUserId(Long id) throws EntityNotFoundException;

    int updateReturnDate(Long issueId);
}
