CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(70) UNIQUE NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    codigo BIGINT NOT NULL,
    grupo_produto_id BIGINT NOT NULL,
    tipo_unidade_id BIGINT NOT NULL,
    fabricante_id BIGINT,
    lucro_sugerido NUMERIC(15, 2) NULL,
    quantidade INTEGER NOT NULL,
    
    CONSTRAINT fk_grupo_produto FOREIGN KEY (grupo_produto_id) REFERENCES grupo_produto(id),
    CONSTRAINT fk_tipo_unidade FOREIGN KEY (tipo_unidade_id) REFERENCES unidade(id),
    CONSTRAINT fk_fabricante FOREIGN KEY (fabricante_id) REFERENCES fabricante(id)
);
