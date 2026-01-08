package com.rsa.expense.tracker.service.currency;

import com.rsa.expense.tracker.integration.privatbank.PrivatBankApiClient;
import com.rsa.expense.tracker.mapper.CurrencyExchangeMapper;
import com.rsa.expense.tracker.model.CurrencyExchange;
import com.rsa.expense.tracker.repository.CurrencyExchangeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {
    private final PrivatBankApiClient privatBankApiClient;
    private final CurrencyExchangeRepository currencyExchangeRepository;
    private final CurrencyExchangeMapper currencyExchangeMapper;

    @Override
    @Transactional
    public void cacheCurrencies() {
        try {
            privatBankApiClient.getCurrencyExchange()
                    .forEach(currencyExchange -> {
                        var entity = currencyExchangeRepository.findByCurrency(currencyExchange.getCurrency()).orElseGet(CurrencyExchange::new);
                        currencyExchangeMapper.toModel(entity, currencyExchange);
                        currencyExchangeRepository.save(entity);
                    });
        } catch (Exception ex) {
            log.error("cacheCurrencies() error caching currencies, message: {}", ex.getMessage(), ex);
        }
    }

}
