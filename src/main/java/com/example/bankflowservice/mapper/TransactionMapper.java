package com.example.bankflowservice.mapper;

import com.example.bankflowservice.dto.TransactionDTO;
import com.example.bankflowservice.model.Transaction;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionDTO toDTO(Transaction transaction);

    Transaction toEntity(TransactionDTO transactionDTO);

    default TransactionDTO toDTO(Optional<Transaction> transactionOptional){
        return transactionOptional.map(this::toDTO).orElse(null);
    }
}
