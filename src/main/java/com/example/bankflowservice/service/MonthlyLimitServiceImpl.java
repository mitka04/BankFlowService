package com.example.bankflowservice.service;

import com.example.bankflowservice.dto.MonthlyLimitDTO;
import com.example.bankflowservice.enums.Currency;
import com.example.bankflowservice.mapper.MonthlyLimitMapper;
import com.example.bankflowservice.model.MonthlyLimit;
import com.example.bankflowservice.repository.MonthlyLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class MonthlyLimitServiceImpl implements MonthlyLimitService{

    private final MonthlyLimitRepository monthlyLimitRepository;
    private final MonthlyLimitMapper monthlyLimitMapper;

    @Autowired
    public MonthlyLimitServiceImpl(MonthlyLimitRepository monthlyLimitRepository, MonthlyLimitMapper monthlyLimitMapper){
        this.monthlyLimitRepository = monthlyLimitRepository;
        this.monthlyLimitMapper = monthlyLimitMapper;
    }

    @Override
    public MonthlyLimitDTO getMonthlyLimit(Long accountId) {
        MonthlyLimit monthlyLimit = monthlyLimitRepository.findById(accountId).orElseGet(MonthlyLimit::new);

        return monthlyLimitMapper.toDTO(monthlyLimit);
    }

    @Override
    public void updateMonthlyLimit(MonthlyLimitDTO updatedLimitDTO) {
        MonthlyLimit existingLimit = monthlyLimitRepository.findById(1L).orElseGet(MonthlyLimit::new);

        // Update only the specific fields
        existingLimit.setAccountId(updatedLimitDTO.getAccountId());
        existingLimit.setCategory(updatedLimitDTO.getCategory());
        existingLimit.setLimitSum(
                updatedLimitDTO.getLimitSum() != null ? updatedLimitDTO.getLimitSum() : BigDecimal.valueOf(1000)
        );
        existingLimit.setCurrencyShortname(Currency.USD);
        existingLimit.setLimitDatetime(
                updatedLimitDTO.getLimitDatetime() != null ? updatedLimitDTO.getLimitDatetime() : LocalDateTime.now()
        );
        monthlyLimitRepository.save(existingLimit);
    }
}
