package com.example.bankflowservice.service;

import com.example.bankflowservice.dto.MonthlyLimitDTO;
import com.example.bankflowservice.enums.Currency;
import com.example.bankflowservice.exceptions.InvalidLimitDatetimeException;
import com.example.bankflowservice.exceptions.LimitAlreadyExistsException;
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
        Long accountIdLong = updatedLimitDTO.getAccountId().longValue();
        MonthlyLimit existingLimit = monthlyLimitRepository.findById(accountIdLong)
                .orElseGet(() -> getDefaultMonthlyLimit(updatedLimitDTO.getAccountId()));

        // Prohibit updating existing limits
        throwIfLimitAlreadyExists(existingLimit);

        // Create a new limit
        MonthlyLimit newLimit = monthlyLimitMapper.toEntity(updatedLimitDTO);

        // Ensure the new limit date is set to the current date
        newLimit.setLimitDatetime(LocalDateTime.now());

        // Prohibit setting limits in the past of future
        throwIfLimitInPastOrFuture(newLimit.getLimitDatetime());

        // Save the new limit
        monthlyLimitRepository.save(newLimit);
    }

    // Additional method to get the default monthly limit if not set
    private MonthlyLimit getDefaultMonthlyLimit(Integer accountId) {
        MonthlyLimit defaultLimit = new MonthlyLimit();
        defaultLimit.setAccountId(accountId);
        defaultLimit.setLimitSum(BigDecimal.valueOf(1000));
        defaultLimit.setCurrencyShortname(Currency.USD);
        defaultLimit.setLimitDatetime(LocalDateTime.now());
        return defaultLimit;
    }

    // Additional method to check if a limit already exists for the give account
    private void throwIfLimitAlreadyExists(MonthlyLimit existingLimit){
        if (existingLimit.getId() != null){
            throw new LimitAlreadyExistsException("Limit already exists for account " +
                    existingLimit.getAccountId());
        }
    }

    // Additional method to check if a limit is in the past or future
    private void throwIfLimitInPastOrFuture(LocalDateTime limitDatetime){
        LocalDateTime now = LocalDateTime.now();
        if (limitDatetime.isBefore(now) || limitDatetime.isAfter(now)){
            throw new InvalidLimitDatetimeException("Limit date must be set to the current date");
        }
    }
}
