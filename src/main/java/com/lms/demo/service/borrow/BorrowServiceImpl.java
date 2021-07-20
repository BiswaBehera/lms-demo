package com.lms.demo.service.borrow;

import com.lms.demo.dao.model.BorrowDetails;
import com.lms.demo.dao.repository.BorrowDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowServiceImpl implements BorrowService{

    @Autowired
    private BorrowDetailsRepository borrowDetailsRepository;

    @Override
    public BorrowDetails saveBorrow(BorrowDetails borrowDetails) {
        return borrowDetailsRepository.save(borrowDetails);
    }
}
