CREATE TABLE saida_item (
    id SERIAL PRIMARY KEY,
    saida_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    quantidade BIGINT NOT NULL,
    valor_unitario DOUBLE PRECISION NOT NULL,
    FOREIGN KEY (saida_id) REFERENCES saida(id),
    FOREIGN KEY (produto_id) REFERENCES produto(id)
);
