create table userposjava(
id bigint not null,
nome character varying(255),
email character varying(255),
constraint user_pk primary key (id)
);

insert into userposjava (id, nome, email) values (1, 'alex', 'alex.fernando.egigio@gmail.com');

create sequence usersequence
increment 1
minvalue 1
maxvalue 9223372036854775807
start 7;

select * from userposjava;

alter table userposjava alter column id set default nextval('usersequence'::regclass);


alter table userposjava add unique (id);

create table telefoneuser(
id bigint not null,
numero character varying(255) not null,
tipo character varying(255) not null,
usuariopessoa bigint not null,
constraint telefone_id primary key (id));

alter table telefoneuser add foreign key (usuariopessoa) references userposjava(id);

create sequence telefoneusersequence
increment 1
minvalue 1
maxvalue 9223372036854775807
start 7;

alter table telefoneuser alter column id set default nextval('telefoneusersequence'::regclass);

insert into telefoneuser(numero, tipo, usuariopessoa) values('(45) 9 9979-5800', 'celular', 1);

select * from telefoneuser as fone inner join userposjava as userp on fone.usuariopessoa = userp.id where userp.id = 1;

delete from userposjava where id = 16;

select * from telefoneuser where usuariopessoa = 16;



)