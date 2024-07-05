CREATE TABLE fornecedor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(70) NOT NULL,
    descricao VARCHAR(255),
    telefone VARCHAR(15) NOT NULL,
    email VARCHAR(120) NOT NULL,
    cnpj VARCHAR(25) NOT NULL,
    bairro VARCHAR(255) NOT NULL,
    cep VARCHAR(20) NOT NULL,
    data_cadastro TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL,
    cidade_id BIGINT NOT NULL,
    endereco VARCHAR(120) NOT NULL,

    CONSTRAINT fk_cliente_cidade FOREIGN KEY (cidade_id) REFERENCES cidade (id)
);
