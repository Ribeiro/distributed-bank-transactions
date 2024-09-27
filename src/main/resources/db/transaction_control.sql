CREATE TABLE transaction_control (
    id BIGSERIAL PRIMARY KEY,
    transaction_key VARCHAR(255) NOT NULL UNIQUE,
    status VARCHAR(50) NOT NULL,
    last_updated TIMESTAMP NOT NULL,
    version BIGINT NOT NULL DEFAULT 0
);
