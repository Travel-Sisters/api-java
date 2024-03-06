drop database ts;
create database ts;
use ts;

select * from usuario;
select * from motorista;
select * from viagem;
select * from empresa;
select * from endereco;
select * from usuario_viagem;

insert into empresa (id) values (1);
insert into empresa (id) values (2);
 
insert endereco (bairro, cidade, end_api, nome ,numero, rua) VALUES
('Mairiporã','São Paulo','300+AV.+Georgetown+São+Paulo+SP', 'Pico Do Olho D-agua' ,300, 'Av. Georgetown'), #Pico Do Olho D'agua
('Tortuga','Guarujá','2050,AV.+Miguel+Estefano+São+Paulo+SP', 'Praia da Enseada' ,2050, 'Av. Miguel Estefano'), #Praia da Enseada
('Copacabana','Rio de janeiro', '2806+Av.+Atlântica+Rio+Janeiro+RJ', 'Praia de Copacabana' ,2806,'Av. Atlântica'), #Praia de Copacabana 
('Bela Vista','Brotas','1090+AV.+Rui+Barbosa+São+Paulo+SP', 'Brotas' ,1090, 'AV.Rui Barbosa'), #Brotas
('Varadouro','São Sebastião','773+Av.+Ver+Antônio+Borges+São+Paulo+SP', 'São sebastião' ,773,'Av. Ver Antônio Borges'), #são sebastião 
('Morada das Flores','Holambra','461+Av.+das+Tulípas+São+Paulo+SP', 'Holambra' ,461,'Av. das Tulípas'), #Holambra	
('Barra Funda','São Paulo','563+Av.+Mário+de+Andrade+São+Paulo+SP', "Terminal barra funda" , 563,'Av. Mário de Andrade'), #Terminal Barra Funda
('Vila do Encontro', 'São Paulo', '152+Av.+Barro+Branco+São+Paulo+SP',"Jabaquara", 152,'Av. Barro Branco'), #Jabaquara
('Canindé','São Paulo','1100+Av.+Cruzeiro+do+Sul+São+Paulo+SP', 'Terminal tietê',1100,'Av. Cruzeiro do Sul'), #terminal tiete 
('Luz', 'São Paulo','596+Av.+Santos+Dumont+São+Paulo+SP',"Luz",596 ,'Av. Santos Dumont'); #Luz


SELECT * FROM viagem WHERE motorista = 1;
select * from usuario where id = 2;

SELECT v.*
FROM viagem v
JOIN usuario_viagem uv 
ON v.id = uv.viagem_id
JOIN usuario u ON uv.usuario_id = u.id
WHERE u.id = '1';

show tables;
