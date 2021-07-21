package com.lms.demo.controller;

import com.lms.demo.dto.borrow.UpdateFineResponse;
import com.lms.demo.service.borrow.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @PutMapping("/borrow/update_fine")
    public UpdateFineResponse refreshFineForBorrows() {
        borrowService.updateFine();

        return new UpdateFineResponse();
    }
}
