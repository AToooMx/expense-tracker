package com.rsa.expense.tracker.scheduling;

import com.rsa.expense.tracker.service.currency.CurrencyExchangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static com.rsa.expense.tracker.constant.AppConstant.TIME_ZONE;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "scheduling.job.currency-exchange-cache", name = "enable", havingValue = "true")
public class CurrencyExchangeCacheSchedulingTask {
    private final CurrencyExchangeService currencyExchangeService;

    @Scheduled(cron = "${scheduling.job.currency-exchange-cache.cron}", zone = TIME_ZONE)
    public void currencyExchangeCacheJob() {
        log.info("currencyExchangeCacheJob() stated: {}", Instant.now());
        currencyExchangeService.cacheCurrencies();
        log.info("currencyExchangeCacheJob() finished: {}", Instant.now());
    }

}
