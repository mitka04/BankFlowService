package com.example.bankflowservice.controller;


import com.example.bankflowservice.dto.TransactionDTO;
import com.example.bankflowservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<String> createTransaction(@RequestBody TransactionDTO transactionDTO){
        // Delegate the creation of the transaction to the service
        transactionService.createTransaction(transactionDTO);

        return ResponseEntity.ok("Transaction saved successfully.");
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions(){
        // Delegate the retrieval of transactions to the service
        List<TransactionDTO> transactionDTOS = transactionService.getAllTransactions();

        return ResponseEntity.ok(transactionDTOS);
    }


}
