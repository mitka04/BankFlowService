package com.example.bankflowservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionDTO {
    private Integer accountFrom;
    private Integer accountTo;
    private String currencyShortname;
    private BigDecimal sum;
    private String expenseCategory;
    private LocalDateTime datetime;
    private boolean limitExceeded;
}
