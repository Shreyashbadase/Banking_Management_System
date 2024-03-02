CREATE DATABASE BankingSystem;

use BankingSystem;

create table Accounts(
accNo bigint primary key,
fullName varchar(30),
EnailId varchar(30) unique,
balance double(7,2) not null,
security_pin char(4));

SELECT * FROM Accounts;
desc Accounts;

create table User(
fullName varchar(30),
EnailId varchar(30) primary key,
password varchar(30));

SELECT * FROM User;
desc User;










