CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(70) NOT NULL UNIQUE,
    descricao VARCHAR(255) NOT NULL,
    codigo BIGINT NOT NULL,
    grupo_produto_id INT NOT NULL,
    tipo_unidade_id INT NOT NULL,
    fabricante_id INT NOT NULL,
    lucro_sugerido DOUBLE PRECISION NOT NULL,
    fornecedor_id INT NOT NULL,
    
    FOREIGN KEY (grupo_produto_id) REFERENCES grupo_produto(id),
    FOREIGN KEY (tipo_unidade_id) REFERENCES unidade(id),
    FOREIGN KEY (fabricante_id) REFERENCES fabricante(id),
    FOREIGN KEY (fornecedor_id) REFERENCES fornecedor(id)
);
