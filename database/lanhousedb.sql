CREATE DATABASE lanhousedb;

USE lanhousedb;

CREATE TABLE cliente (
	id INT(11),
	nome VARCHAR(255),
    email VARCHAR(255),
    endereco VARCHAR(255)
);


CREATE TABLE aluguel (
  idaluguel int(11) NOT NULL,
  nomecliente varchar(255) NOT NULL,
  idcomputador int(11) NOT NULL,
  qtdhoras varchar(255) NOT NULL
);


CREATE TABLE computador (
  id int(11) NOT NULL,
  status varchar(255) NOT NULL
);


CREATE TABLE funcionario (
  idfuncionario int(11) NOT NULL,
  nomefuncionario varchar(255) NOT NULL,
  emailfuncionario varchar(255) NOT NULL,
  enderecofuncionario varchar(255) NOT NULL
);

