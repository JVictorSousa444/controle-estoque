CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(70) NOT NULL,
    documento VARCHAR(20) NOT NULL,
    cpf_cnpj VARCHAR(25) NOT NULL UNIQUE,
    endereco VARCHAR(120) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    telefone VARCHAR(22) NOT NULL,
    status VARCHAR(10) NOT NULL
);

alter table Cliente add constraint UK_cpf_cpnj unique (cpf_cnpj);
alter table Cliente add constraint UK_telefone unique (telefone);
alter table Cliente add constraint UK_email unique (email);