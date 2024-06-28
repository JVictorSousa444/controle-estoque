CREATE TABLE estoque (
    id SERIAL PRIMARY KEY,
    produto_id BIGINT NOT NULL,
    quantidade BIGINT NOT NULL CHECK (quantidade >= 1),
    CONSTRAINT fk_produto FOREIGN KEY (produto_id) REFERENCES produto(id)
);
