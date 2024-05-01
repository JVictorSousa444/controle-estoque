CREATE TABLE entrada_item (
    id SERIAL PRIMARY KEY,
    entrada_id BIGINT,
    produto_id BIGINT,
    quantidade BIGINT NOT NULL,
    valor_unitario DOUBLE PRECISION NOT NULL,
    FOREIGN KEY (entrada_id) REFERENCES entrada(id),
    FOREIGN KEY (produto_id) REFERENCES produto(id)
);
