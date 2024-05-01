CREATE TABLE fabricante (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(70) NOT NULL,
    descricao VARCHAR(255),
    documento VARCHAR(20) NOT NULL,
    num_cpf_cnpj VARCHAR(14) NOT NULL UNIQUE
);
