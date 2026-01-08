package com.rsa.expense.tracker.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "currency_exchange")
public class CurrencyExchange extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    private Currency currency;

    @Column(name = "rate_buy", nullable = false)
    private Double rateBuy;

    @Column(name = "rate_sell", nullable = false)
    private Double rateSell;
}
