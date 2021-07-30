package com.lms.demo.service.borrow;

import com.lms.demo.dao.model.BorrowDetails;
import com.lms.demo.dao.model.User;
import com.lms.demo.dao.repository.BorrowDetailsRepository;
import com.lms.demo.error.EntityNotFoundException;
import com.lms.demo.error.ErrorResponseMessages;
import com.lms.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowServiceImpl implements BorrowService{

    @Autowired
    private BorrowDetailsRepository borrowDetailsRepository;

    @Autowired
    private UserService userService;

    @Override
    public BorrowDetails saveBorrow(BorrowDetails borrowDetails) {
        return borrowDetailsRepository.save(borrowDetails);
    }

    @Override
    public BorrowDetails fetchByIssueId(Long id) throws EntityNotFoundException {
        Optional<BorrowDetails> borrowDetails = borrowDetailsRepository.findById(id);
        if(borrowDetails.isPresent()) {
            return borrowDetails.get();
        } else {
            throw new EntityNotFoundException(ErrorResponseMessages.borrowDetailsNotFound);
        }
    }

    @Override
    @Transactional
    public void updateFine() {
        List<BorrowDetails> list = borrowDetailsRepository.findByReturnDateAndDueDate(null, new Date(System.currentTimeMillis()));

        for(BorrowDetails bd : list) {
            Date dueDate = bd.getDueDate();
            Date currentDate = new Date(System.currentTimeMillis());
            if(currentDate.getTime() > dueDate.getTime()) {
                long day_diff = ((currentDate.getTime()- dueDate.getTime()) / (1000 * 60 * 60 * 24));
                int fine = (int) day_diff * 10;

                bd.setFine(fine);
            }
        }
    }
    @Override
    @Transactional
    public void updateFine(BorrowDetails borrowDetails) {
        Date dueDate = borrowDetails.getDueDate();
        Date currentDate = new Date(System.currentTimeMillis());
        if(currentDate.getTime() > dueDate.getTime()) {
            long day_diff = ((currentDate.getTime() - dueDate.getTime()) / (1000 * 60 * 60 * 24));
            int fine = (int) day_diff * 10;

            borrowDetails.setFine(fine);
        }
    }

    @Override
    public List<BorrowDetails> fetchActiveBorrowsByUserId(Long id) throws EntityNotFoundException {

        User user = userService.getUserById(id);

        return borrowDetailsRepository.findByUserAndReturnDate(user, null);
    }

//    @Override
//    public int updateReturnDate(Long issueId) {
//        return borrowDetailsRepository.updateReturnDateByIssueId(issueId, new Date(System.currentTimeMillis()));
//    }

    public void updateReturnDate(BorrowDetails borrowDetails) {
        borrowDetails.setReturnDate(new Date(System.currentTimeMillis()));
    }
}
