package com.example.bankflowservice.controller;

import com.example.bankflowservice.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exchange-rates")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @Autowired
    public ExchangeRateController(ExchangeRateService exchangeRateService){
        this.exchangeRateService = exchangeRateService;
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateExchangeRates(){
        exchangeRateService.updateExchangeRatesForPair();
        return ResponseEntity.ok("Exchange rates updated successfully");
    }
}
