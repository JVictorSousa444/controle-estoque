CREATE TABLE entrada (
    id SERIAL PRIMARY KEY,
    data_entrada TIMESTAMP NOT NULL,
    data_pagamento TIMESTAMP,
    data_vencimento TIMESTAMP,
    fornecedor_id BIGINT NOT NULL,
    CONSTRAINT fk_fornecedor FOREIGN KEY (fornecedor_id) REFERENCES fornecedor(id)
);
