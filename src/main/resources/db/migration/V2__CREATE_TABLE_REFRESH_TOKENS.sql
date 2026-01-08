CREATE TABLE refresh_tokens(
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    created_at TIMESTAMP WITH TIME ZONE,
    updated_at TIMESTAMP WITH TIME ZONE,
    expired_at TIMESTAMP WITH TIME ZONE NOT NULL,
    token VARCHAR(128) NOT NULL,
    user_id INT REFERENCES USERS (id)
);

CREATE INDEX idx_refresh_tokens_user_id ON refresh_tokens(user_id);