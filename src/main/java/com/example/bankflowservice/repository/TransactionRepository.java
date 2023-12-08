package com.example.bankflowservice.repository;

import com.example.bankflowservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Modifying
    @Query("UPDATE Transaction t SET t.limitExceeded = :limitExceeded WHERE t.id = :transactionId")
    void updateLimitExceeded(@Param("transactionId") Long transactionId, @Param("limitExceeded") boolean limitExceeded);
}
