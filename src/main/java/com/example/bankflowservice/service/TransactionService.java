package com.example.bankflowservice.service;

import com.example.bankflowservice.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {

    void createTransaction(TransactionDTO transactionDTO);

    List<TransactionDTO> getAllTransactions();
}
