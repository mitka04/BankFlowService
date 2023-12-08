package com.example.bankflowservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "exchange_rates")
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "base_currency")
    private String baseCurrency;

    @Column(name = "target_currency")
    private String targetCurrency;

    private BigDecimal rate;

    private LocalDate date;
}
