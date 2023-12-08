package com.example.bankflowservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ExchangeRateDTO {
    private String baseCurrency;
    private String targetCurrency;
    private BigDecimal rate;
    private LocalDate date;

}
