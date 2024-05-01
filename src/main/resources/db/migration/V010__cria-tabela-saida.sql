CREATE TABLE saida (
    id SERIAL PRIMARY KEY,
    data TIMESTAMP NOT NULL,
    data_vencimento TIMESTAMP NOT NULL,
    cliente_id BIGINT NOT NULL,
    
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);
