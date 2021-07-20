package com.lms.demo.dao.repository;

import com.lms.demo.dao.model.BorrowDetails;
import com.lms.demo.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowDetailsRepository extends JpaRepository<BorrowDetails, Long> {


    List<BorrowDetails> findByUserAndReturnDate(User user, Date returnDate);

    @Modifying
    @Query("UPDATE BorrowDetails bd SET bd.returnDate = :returnDate WHERE bd.id = :issueId")
    int updateReturnDateByIssueId(@Param("issueId") Long issueId, @Param("returnDate") Date returnDate);
}
