package com.example.bankflowservice.openexchangerates;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
public class OpenExchangeRatesResponse {

    private String disclaimer;
    private String license;
    private long timestamp;
    private String base;
    private Map<String, BigDecimal> rates;
}

