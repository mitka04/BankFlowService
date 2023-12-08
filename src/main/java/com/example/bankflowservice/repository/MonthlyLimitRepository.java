package com.example.bankflowservice.repository;

import com.example.bankflowservice.model.MonthlyLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyLimitRepository extends JpaRepository<MonthlyLimit, Long> {
}
