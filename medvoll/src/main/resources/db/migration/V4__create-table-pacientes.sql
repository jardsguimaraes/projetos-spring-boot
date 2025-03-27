CREATE Table pacientes (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(1000) NOT NULL,
    telefone VARCHAR(16) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    uf char(2) not null,
    cidade varchar(100) not null,
    ativo TINYINT,

    primary key(id)
);