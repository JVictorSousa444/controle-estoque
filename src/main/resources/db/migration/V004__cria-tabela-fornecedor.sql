CREATE TABLE fornecedor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(70) NOT NULL,
    descricao VARCHAR(255),
    telefone VARCHAR(15) NOT NULL,
    email VARCHAR(120) NOT NULL
);
