package com.rsa.expense.tracker.integration.privatbank;

import com.rsa.expense.tracker.integration.privatbank.dto.CurrencyExchangeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RequiredArgsConstructor
public class PrivatBankApiClient {
    private final WebClient webClient;

    public List<CurrencyExchangeResponse> getCurrencyExchange() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/pubinfo")
                        .queryParam("course", 11)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CurrencyExchangeResponse>>() {
                })
                .block();
    }
}
