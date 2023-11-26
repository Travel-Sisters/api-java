drop database ts;
create database ts;
use ts;

select * from usuario;
select * from motorista;
select * from viagem;
select * from empresa;
select * from endereco;

insert into empresa (id) values (1);
insert into empresa (id) values (2);
insert into empresa (id) values (3);

 
insert endereco (bairro, cidade, end_api, numero, rua) VALUES
('Mairiporã','São Paulo','300+AV.+Georgetown+São+Paulo+SP', 'Pico Do Olho D-agua' ,300, 'Av. Georgetown'), #Pico Do Olho D'agua
('Tortuga','Guarujá','2050,AV.+Miguel+Estefano+São+Paulo+SP', 'Praia da Enseada' ,2050, 'Av. Miguel Estefano'), #Praia da Enseada
('Copacabana','Rio de janeiro', '2806+Av.+Atlântica+Rio+Janeiro+RJ', 'Praia de Copacabana' ,2806,'Av. Atlântica'), #Praia de Copacabana 
('Bela Vista','Brotas','1090+AV.+Rui+Barbosa+São+Paulo+SP', 'Brotas' ,1090, 'AV.Rui Barbosa'), #Brotas
('Varadouro','São Sebastião','773+Av.+Ver+Antônio+Borges+São+Paulo+SP', 'São sebastião' ,773,'Av. Ver Antônio Borges'), #são sebastião 
('Morada das Flores','Holambra','461+Av.+das+Tulípas+São+Paulo+SP', 'Holambra' ,461,'Av. das Tulípas'); #Holambra	
 
 insert into viagem(data_viagem, descricao, horario, status_viagem,valor,motorista,ponto_desembarque,ponto_embarque, usuario) values
 ('2025-8-10',  'ponto a pra b', '15:00','em progresso', 500.00, 1, 1, 2, 1),
('2001-05-10',  'ponto a pra b', '15:00','em progresso', 500.00, 1, 1, 2, 1);

SELECT * FROM viagem WHERE motorista = 1;

show tables;
