CREATE TABLE transactions(
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    created_at TIMESTAMP WITH TIME ZONE,
    updated_at TIMESTAMP WITH TIME ZONE,
    amount DOUBLE PRECISION NOT NULL,
    description TEXT NOT NULL,
    type VARCHAR(32) NOT NULL,
    category VARCHAR(32) NOT NULL,
    user_id INT REFERENCES USERS (id)
);

CREATE INDEX idx_transactions_user_id ON transactions(user_id);
CREATE INDEX idx_transactions_created_at ON transactions(created_at);