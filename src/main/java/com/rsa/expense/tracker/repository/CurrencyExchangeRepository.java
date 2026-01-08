package com.rsa.expense.tracker.repository;

import com.rsa.expense.tracker.model.Currency;
import com.rsa.expense.tracker.model.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

    Optional<CurrencyExchange> findByCurrency(Currency currency);

}
