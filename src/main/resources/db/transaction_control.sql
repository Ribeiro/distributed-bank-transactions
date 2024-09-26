CREATE TABLE transaction_control (
    transaction_id BIGSERIAL PRIMARY KEY,
    transaction_key VARCHAR(255) NOT NULL UNIQUE,  -- Chave única para cada transação
    status VARCHAR(50) NOT NULL,                   -- Status da transação: "IN_PROGRESS", "COMPLETED", "FAILED"
    last_updated TIMESTAMP NOT NULL DEFAULT NOW()   -- Data da última atualização
);