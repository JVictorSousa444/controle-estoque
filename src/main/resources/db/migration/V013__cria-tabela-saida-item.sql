CREATE TABLE saida_item (
    id SERIAL PRIMARY KEY,
    saida_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    quantidade BIGINT NOT NULL CHECK (quantidade >= 1),
    valor_unitario NUMERIC(15, 2) NOT NULL CHECK (valor_unitario >= 0.01),
    CONSTRAINT fk_saida FOREIGN KEY (saida_id) REFERENCES saida(id),
    CONSTRAINT fk_produto FOREIGN KEY (produto_id) REFERENCES produto(id)
);
