package com.example.bankflowservice.controller;

import com.example.bankflowservice.dto.MonthlyLimitDTO;
import com.example.bankflowservice.service.MonthlyLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/monthly-limit")
public class MonthlyLimitController {

    private final MonthlyLimitService monthlyLimitService;

    @Autowired
    public MonthlyLimitController(MonthlyLimitService monthlyLimitService){
        this.monthlyLimitService = monthlyLimitService;
    }

    @GetMapping
    public MonthlyLimitDTO getMonthlyLimit(@RequestParam Long accountId){
        return monthlyLimitService.getMonthlyLimit(accountId);
    }

    @PutMapping
    public void updateMonthlyLimit(@RequestBody MonthlyLimitDTO updatedLimitDTO){
        monthlyLimitService.updateMonthlyLimit(updatedLimitDTO);
    }
}
