CREATE TABLE subgrupo_produto (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255),
    grupo_produto_id INT NOT NULL,
    
    FOREIGN KEY (grupo_produto_id) REFERENCES grupo_produto(id)
);