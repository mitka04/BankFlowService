package com.example.bankflowservice.service;

import com.example.bankflowservice.dto.MonthlyLimitDTO;
import com.example.bankflowservice.dto.TransactionDTO;

import com.example.bankflowservice.mapper.TransactionMapper;
import com.example.bankflowservice.model.Transaction;
import com.example.bankflowservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService{

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final MonthlyLimitService monthlyLimitService;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper, MonthlyLimitService monthlyLimitService){
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.monthlyLimitService = monthlyLimitService;
    }

    @Override
    @Transactional
    public void createTransaction(TransactionDTO transactionDTO){
        // Delegate the creation of the transaction to the repository
        Transaction transaction = transactionRepository.save(transactionMapper.toEntity(transactionDTO));

        // Mark exceeded transactions based on the monthly limit
        markExceededTransactions(transaction.getId());
    }

    private void markExceededTransactions(Long transactionId) {
        TransactionDTO transactionDTO = transactionMapper.toDTO(transactionRepository.findById(transactionId));
        Long accountId = Long.valueOf(transactionDTO.getAccountFrom());

        // Retrieving the monthly limit for the account
        MonthlyLimitDTO monthlyLimitDTO = monthlyLimitService.getMonthlyLimit(accountId);

        if (monthlyLimitDTO != null){
            BigDecimal remainingLimit = monthlyLimitDTO.getLimitSum().subtract(transactionDTO.getSum());

            // Mark the transaction as exceeded if the remaining limit is negative
            boolean limitExceeded = remainingLimit.compareTo(BigDecimal.ZERO) < 0;

            transactionRepository.updateLimitExceeded(transactionId, limitExceeded);
        }
    }

    @Override
    public List<TransactionDTO> getAllTransactions(){
        // Retrieve all transactions from the database
        List<Transaction> transactions = transactionRepository.findAll();

        // Map Entity list to DTO list using the mapper
        return transactions.stream()
                .map(transactionMapper::toDTO)
                .collect(Collectors.toList());
    }
}
