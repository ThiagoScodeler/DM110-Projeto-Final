create table equipamentos (
endereco varchar(80) not null,
status varchar(20) not null,
constraint pk_equipamentos primary key (endereco)
);
create sequence seq_equipamentos;