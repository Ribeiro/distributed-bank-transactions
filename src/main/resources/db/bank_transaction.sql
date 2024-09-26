CREATE TABLE bank_transaction (
    id BIGSERIAL PRIMARY KEY,
    account_id BIGINT NOT NULL,           -- ID da conta bancária
    transaction_amount DECIMAL(18, 2) NOT NULL, -- Valor da transação
    transaction_type VARCHAR(50) NOT NULL, -- Tipo da transação: "DEPOSIT" ou "WITHDRAW"
    transaction_date TIMESTAMP NOT NULL DEFAULT NOW(),
    UNIQUE (account_id, transaction_date)  -- Evita múltiplas transações duplicadas no mesmo instante
);