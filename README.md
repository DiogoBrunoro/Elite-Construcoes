# Elite construções

Nós da Elite contruções, temos como objetivo facilitar os processos envolvidos na gestão de uma obra, com o foco de otimizar serviços e facilitar a comunição entre contrutura e cliente

## Integrantes

* Bernardo Melgaço Soares
* Diogo Caribé Brunouro
* Lucas Ferreira Ribeiro
* Paulo Henrique Fonseca de Assis
* Pedro Henrique Maia
* Pedro Rodrigues Duarte

## Professor

* Aline Norberta de Brito
* Eveline Alonso Veloso
* Juliana Amaral Baroni de Carvalho

## Instruções de utilização

# Script de Banco de Dados para Elite Construções

## Criando o Banco de Dados
```sql
CREATE DATABASE banco_elite_construcoes;

USE banco_elite_construcoes;

CREATE TABLE cliente (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    nome_completo VARCHAR(200) NOT NULL,
    nome_de_usuario VARCHAR(50) NOT NULL,
    data_de_nascimento DATE,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(225) NOT NULL
);


CREATE TABLE fornecedor (
    id_fornecedor INTEGER PRIMARY KEY AUTO_INCREMENT,
    cnpj VARCHAR(14) UNIQUE NOT NULL,
    nome_fornecedor VARCHAR(100) NOT NULL,
    endereco VARCHAR(100) NOT NULL,
    email_corporativo VARCHAR(100) UNIQUE NOT NULL,
    senha_fornecedor VARCHAR(225) NOT NULL
);


CREATE TABLE servico (
    id_servico INTEGER PRIMARY KEY AUTO_INCREMENT,
    nome_do_servico VARCHAR(100) NOT NULL,
    preco DOUBLE NOT NULL,
    unidade_de_medida VARCHAR(10) NOT NULL,
    descricao VARCHAR(500) NOT NULL,
    id_fornecedor INTEGER,
    FOREIGN KEY (id_fornecedor) REFERENCES fornecedor(id_fornecedor)
);


CREATE TABLE obra (
    id_obra INTEGER PRIMARY KEY AUTO_INCREMENT,
    nome_da_obra VARCHAR(50),
    data_inicio DATE,
    data_fim DATE,
    preco DOUBLE NOT NULL,
    area_construida DOUBLE NOT NULL,
    endereco VARCHAR(500) NOT NULL,
    observacoes_cliente VARCHAR(500) NOT NULL,
    id_servico INTEGER,
    id_cliente INTEGER,
    estado VARCHAR(20),
    no_prazo BOOLEAN,
    FOREIGN KEY (id_servico) REFERENCES servico(id_servico),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);

SELECT * FROM obra;


CREATE TABLE pagamento (
    id_pagamento INTEGER PRIMARY KEY AUTO_INCREMENT,
    numero_cartao VARCHAR(17),
    nome_cartao VARCHAR(50),
    validade_cartao VARCHAR(10),
    cvv VARCHAR(3)
);

CREATE TABLE gestao_de_obra (
    id_gestao_obra INTEGER AUTO_INCREMENT PRIMARY KEY,
    id_obra INTEGER,
    data_atualizacao DATE,
    barra_progresso DOUBLE,
    comentario VARCHAR(500),
    FOREIGN KEY (id_obra) REFERENCES obra(id_obra)
);
```



## Histórico de versões

* 0.1.1
    * CHANGE: Atualização das documentações. Código permaneceu inalterado.
* 0.1.0
    * Implementação da funcionalidade X pertencente ao processo P.
* 0.0.1
    * Trabalhando na modelagem do processo de negócio.

