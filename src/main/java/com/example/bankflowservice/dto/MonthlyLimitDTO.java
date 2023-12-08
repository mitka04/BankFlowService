package com.example.bankflowservice.dto;

import com.example.bankflowservice.enums.ExpenseCategory;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class MonthlyLimitDTO {
    private Integer accountId;
    private ExpenseCategory category;
    private BigDecimal limitSum;
    private LocalDateTime limitDatetime;
}
