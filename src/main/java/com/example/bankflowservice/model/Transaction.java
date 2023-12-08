package com.example.bankflowservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_from")
    private Integer accountFrom;

    @Column(name = "account_to")
    private Integer accountTo;

    @Column(name = "currency_shortname")
    private String currencyShortname;

    private BigDecimal sum;

    @Column(name = "expense_category")
    private String expenseCategory;

    private LocalDateTime datetime;

    @Column(name = "limit_exceeded")
    private boolean limitExceeded;

    public void setSum(BigDecimal sum){
        this.sum = sum.setScale(2, RoundingMode.HALF_UP);
    }
}
