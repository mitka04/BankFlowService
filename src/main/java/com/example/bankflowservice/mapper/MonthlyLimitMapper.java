package com.example.bankflowservice.mapper;

import com.example.bankflowservice.dto.MonthlyLimitDTO;
import com.example.bankflowservice.model.MonthlyLimit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MonthlyLimitMapper {

    MonthlyLimitDTO toDTO(MonthlyLimit monthlyLimit);

    MonthlyLimit toEntity(MonthlyLimitDTO monthlyLimitDTO);
}
