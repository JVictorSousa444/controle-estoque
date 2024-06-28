CREATE TABLE saida (
    id SERIAL PRIMARY KEY,
    data TIMESTAMP NOT NULL,
    data_vencimento TIMESTAMP,
    data_pagamento TIMESTAMP,
    cliente_id BIGINT NOT NULL,
    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);
