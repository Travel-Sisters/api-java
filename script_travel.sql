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
('Jardim Paulista', 'São Paulo' , '3000+Av.+Nove+de+Julho,+São+Paulo,+SP',3000,'Av. Nove de Julho');

insert into viagem(data_viagem, descricao, horario,valor,motorista ,ponto_desembarque,ponto_embarque, usuario) values
 ('2021-12-05',  'ponto a pra b', '14:00', 200.00, 1, 1, 1, 1);

SELECT * FROM viagem WHERE motorista = 1;

show tables;
