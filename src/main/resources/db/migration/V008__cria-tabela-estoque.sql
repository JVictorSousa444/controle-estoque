CREATE TABLE estoque (
    id SERIAL PRIMARY KEY,
    produto_id INT NOT NULL,
    quantidade BIGINT NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES produto(id)
);
