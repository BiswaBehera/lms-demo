package com.lms.demo.service.borrow;

import com.lms.demo.dao.model.BorrowDetails;
import com.lms.demo.dao.model.User;
import com.lms.demo.dao.repository.BorrowDetailsRepository;
import com.lms.demo.error.EntityNotFoundException;
import com.lms.demo.error.ErrorResponseMessages;
import com.lms.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public BorrowDetails updateFineWithoutPersist(BorrowDetails borrowDetails) {
        Date dueDate = borrowDetails.getDueDate();
        if(System.currentTimeMillis() > dueDate.getTime()) {
            long day_diff = ((System.currentTimeMillis() - dueDate.getTime()) / (1000*60*60*24)) % 365;
            borrowDetails.setFine((int) day_diff * 10);
        }
        return null;
    }

    @Override
    public List<BorrowDetails> fetchActiveBorrowsByUserId(Long id) throws EntityNotFoundException {

        Optional<User> user = userService.getUserById(id);

        if(user.isEmpty()) {
            throw new EntityNotFoundException(ErrorResponseMessages.userNotFound);
        }

        List<BorrowDetails> activeBorrowDetailsList =
                borrowDetailsRepository.findByUserAndReturnDate(user.get(), null);

        for(BorrowDetails b: activeBorrowDetailsList) {
            updateFineWithoutPersist(b);
        }

        return activeBorrowDetailsList;
    }
}
