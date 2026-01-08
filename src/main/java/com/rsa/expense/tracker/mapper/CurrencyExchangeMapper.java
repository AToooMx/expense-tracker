package com.rsa.expense.tracker.mapper;

import com.rsa.expense.tracker.integration.privatbank.dto.CurrencyExchangeResponse;
import com.rsa.expense.tracker.model.CurrencyExchange;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CurrencyExchangeMapper {

    @Mapping(source = "currency", target = "currency")
    @Mapping(source = "buy", target = "rateBuy")
    @Mapping(source = "sale", target = "rateSell")
    CurrencyExchange toModel(@MappingTarget CurrencyExchange entity, CurrencyExchangeResponse dto);

}
