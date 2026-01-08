package com.rsa.expense.tracker.integration.privatbank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rsa.expense.tracker.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyExchangeResponse {
    @JsonProperty("ccy")
    private Currency currency;
    @JsonProperty("base_ccy")
    private Currency baseCcy;
    @JsonProperty("buy")
    private Double buy;
    @JsonProperty("sale")
    private Double sale;
}
