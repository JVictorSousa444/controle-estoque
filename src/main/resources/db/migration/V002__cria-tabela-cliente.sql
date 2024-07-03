CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(70) NOT NULL,
    documento VARCHAR(255) NOT NULL,
    cpf_cnpj VARCHAR(25) UNIQUE NOT NULL,
    endereco VARCHAR(120) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    status VARCHAR(255) NOT NULL,
    cidade_id BIGINT NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    CONSTRAINT fk_cliente_cidade FOREIGN KEY (cidade_id) REFERENCES cidade (id)
);

-- Tabela para telefones dos clientes
CREATE TABLE cliente_telefones (
    cliente_id BIGINT NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    PRIMARY KEY (cliente_id, telefone),
    CONSTRAINT fk_cliente_telefones_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (id)
);
