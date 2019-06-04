drop database if exists eletronicagenda;
create database eletronicagenda;
use eletronicagenda;
create table contact(
	id integer auto_increment primary key,
	username varchar(50) not null,
	email varchar(50) not null unique,
	telephone varchar(50) not null,
    marital_status varchar(10) not null
);

desc contact;