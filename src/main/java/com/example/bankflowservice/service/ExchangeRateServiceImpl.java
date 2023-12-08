package com.example.bankflowservice.service;

import com.example.bankflowservice.enums.Currency;
import com.example.bankflowservice.model.ExchangeRate;
import com.example.bankflowservice.openexchangerates.OpenExchangeRatesResponse;
import com.example.bankflowservice.dto.ExchangeRateDTO;
import com.example.bankflowservice.mapper.ExchangeRateMapper;
import com.example.bankflowservice.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;
    private final ExchangeRateMapper exchangeRateMapper;
    private final WebClient webClient;

    @Value("${openexchangerates.org.app-id}")
    private String openExchangeRatesAppId;

    private final String OPEN_EXCHANGE_RATES_API_BASE_URL = "https://openexchangerates.org/api/latest.json";

    @Autowired
    public ExchangeRateServiceImpl(ExchangeRateRepository exchangeRateRepository, ExchangeRateMapper exchangeRateMapper, WebClient webClient){
        this.exchangeRateRepository = exchangeRateRepository;
        this.exchangeRateMapper = exchangeRateMapper;
        this.webClient = webClient;
    }

    @Override
    public void updateExchangeRatesForPair() {

        List<Currency> targetCurrencies = Arrays.asList(Currency.KZT, Currency.RUB);

        // API URL
        String apiUrl = String.format("%s?app_id=%s",
                OPEN_EXCHANGE_RATES_API_BASE_URL, openExchangeRatesAppId);

        // API request using WebClient
        OpenExchangeRatesResponse openExchangeRatesResponse = webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(OpenExchangeRatesResponse.class)
                .block();

        List<ExchangeRate> exchangeRates = new ArrayList<>();

        if (openExchangeRatesResponse != null && openExchangeRatesResponse.getRates() != null){
            for (Currency targetCurrency : targetCurrencies) {
                BigDecimal rate = openExchangeRatesResponse.getRates().get(targetCurrency.name());

                if (rate != null) {
                    ExchangeRateDTO exchangeRateDTO = new ExchangeRateDTO();
                    exchangeRateDTO.setBaseCurrency(Currency.USD.name());
                    exchangeRateDTO.setTargetCurrency(targetCurrency.name());
                    exchangeRateDTO.setRate(rate);
                    exchangeRateDTO.setDate(LocalDate.now());

                    // Add ExchangeRate entity to the list
                    exchangeRates.add(exchangeRateMapper.toEntity(exchangeRateDTO));
                }
            }

            // Save all ExchangeRate entities in a batch
            if (exchangeRateRepository != null)
                exchangeRateRepository.saveAll(exchangeRates);
        }
    }

    @Override
    public BigDecimal calculateExpenseInUSD(BigDecimal amount, String baseCurrency, LocalDate expenseDate){
        return null;
    }

    @Override
    public BigDecimal getExchangeRate(String baseCurrency, String targetCurrency, LocalDate date) {
        return null;
    }
}
