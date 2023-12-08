package com.example.bankflowservice.service;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ExchangeRateService {

    void updateExchangeRatesForPair();

    BigDecimal calculateExpenseInUSD(BigDecimal amount, String baseCurrency, LocalDate expenseDate);

    BigDecimal getExchangeRate(String baseCurrency, String targetCurrency, LocalDate date);
}
