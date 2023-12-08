package com.example.bankflowservice.mapper;

import com.example.bankflowservice.dto.ExchangeRateDTO;
import com.example.bankflowservice.model.ExchangeRate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExchangeRateMapper {

    ExchangeRateDTO toDTO(ExchangeRate exchangeRate);

    ExchangeRate toEntity(ExchangeRateDTO exchangeRateDTO);
}
