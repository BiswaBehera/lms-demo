package com.lms.demo.dao.repository;

import com.lms.demo.dao.model.BorrowDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowDetailsRepository extends JpaRepository<BorrowDetails, Long> {
}
