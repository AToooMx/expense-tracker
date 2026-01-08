CREATE TABLE currency_exchange(
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    created_at TIMESTAMP WITH TIME ZONE,
    updated_at TIMESTAMP WITH TIME ZONE,
    currency VARCHAR(32) NOT NULL,
    rate_buy DOUBLE PRECISION NOT NULL,
    rate_sell DOUBLE PRECISION NOT NULL
);

CREATE INDEX idx_currency_exchange_currency ON currency_exchange(currency);

