create database sismed;

create table usuarios (
id int not null auto_increment,
login varchar(20),
senha varchar(10),
nome varchar(20),
cpf varchar(20),
dataNascimento varchar(20),
email varchar(50),
telefone varchar(20),
estado boolean,
especialidade varchar(20),
sala varchar(5)


);