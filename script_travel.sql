drop database ts;
create database ts;
use ts;

truncate motorista;
truncate viagem;
truncate usuario;
truncate endereco;

select * from usuario;
select * from motorista;
select * from viagem;
select * from empresa;
select * from endereco;

insert into empresa (id) values (1);

insert into usuario(cpf, email, nascimento,nome ,senha) values
 ('503.027.138-40',  'isabellaneves10@hotmail.com', '2001-06-12', 'Isabella', '123');
 
 insert into motorista(cnh,placa_van,empresa,usuario) values
 ('123456', 'abc456', 1,1);
 
insert endereco (bairro, cidade, end_api, numero, rua) VALUES
( 'Jardim Malia II', 'São Paulo', '1670+Av.+Pres.+João+Goulart,+São+Paulo,+SP', 1670,'Av. Pres. João Goulart' ),
('Jardim Paulista', 'São Paulo' , '3000+Av.+Nove+de+Julho,+São+Paulo,+SP',3000,'Av. Nove de Julho'),
('Mairiporã','São Paulo','300+AV.+Georgetown+São+Paulo+SP', 300, 'Av. Georgetown'), #Pico Do Olho D'agua
('Tortuga','Guarujá','2050,AV.+Miguel+Estefano+São+Paulo+SP', 2050, 'Av. Miguel Estefano'), #Praia da Enseada
('Copacabana','Rio de janeiro', '2806+Av.+Atlântica+Rio+Janeiro+RJ', 2806,'Av. Atlântica'), #Praia de Copacabana 
('Bela Vista','Brotas','1090+AV.+Rui+Barbosa+São+Paulo+SP', 1090, 'AV.Rui Barbosa'), #Brotas
('Varadouro','São Sebastião','773+Av.+Ver+Antônio+Borges+São+Paulo+SP', 773,'Av. Ver Antônio Borges'), #são sebastião 
('Morada das Flores','Holambra','461+Av.+das+Tulípas+São+Paulo+SP',461,'Av. das Tulípas'); #Holambra	

insert into viagem(data_viagem, descricao, horario, status_viagem,valor,motorista ,ponto_desembarque,ponto_embarque, usuario) values
 ('2021-12-05',  'ponto a pra b', '14:00','em progresso', 200.00, 1, 2, 1, 1);
 
 insert into viagem(data_viagem, descricao, horario, status_viagem,valor,motorista,ponto_desembarque,ponto_embarque, usuario) values
 ('2025-8-10',  'ponto a pra b', '15:00','em progresso', 500.00, 1, 1, 2, 1),
  ('2001-05-10',  'ponto a pra b', '15:00','em progresso', 500.00, 1, 1, 2, 1);z

SELECT * FROM viagem WHERE motorista = 1;

show tables;
