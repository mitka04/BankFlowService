package com.example.bankflowservice.model;

import com.example.bankflowservice.enums.Currency;
import com.example.bankflowservice.enums.ExpenseCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "limits")
public class MonthlyLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id")
    private Integer accountId;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private ExpenseCategory category;

    @Column(name = "limit_sum")
    private BigDecimal limitSum;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency_shortname")
    private Currency currencyShortname;

    @Column(name = "limit_datetime")
    private LocalDateTime limitDatetime;
}
