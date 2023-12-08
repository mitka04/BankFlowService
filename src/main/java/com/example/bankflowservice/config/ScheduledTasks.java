package com.example.bankflowservice.config;

import com.example.bankflowservice.service.ExchangeRateService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduledTasks {
    private final ExchangeRateService exchangeRateService;

    public ScheduledTasks(ExchangeRateService exchangeRateService){
        this.exchangeRateService = exchangeRateService;
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void updateExchangeRates(){
        exchangeRateService.updateExchangeRatesForPair();
    }
}
