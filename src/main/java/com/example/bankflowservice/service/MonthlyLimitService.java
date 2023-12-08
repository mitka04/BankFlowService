package com.example.bankflowservice.service;

import com.example.bankflowservice.dto.MonthlyLimitDTO;

public interface MonthlyLimitService {

    MonthlyLimitDTO getMonthlyLimit(Long accountId);

    void updateMonthlyLimit(MonthlyLimitDTO updatedMonthlyLimit);
}
