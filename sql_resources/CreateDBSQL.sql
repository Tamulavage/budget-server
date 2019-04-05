# mysql -u root -p

CREATE DATABASE budget;

USE budget;

CREATE SCHEMA budget;

CREATE TABLE user (
	 user_id int NOT NULL auto_increment,
	 name varchar(50) NOT NULL,
	 CONSTRAINT pk_user PRIMARY KEY(user_id)
);

CREATE TABLE account (
	account_id int NOT NULL auto_increment,
	account_type_id int NOT Null,
	institution varchar(100),
	nickname varchar(100),
	balance double ,
	CONSTRAINT pk_account PRIMARY KEY(account_id)
);

CREATE TABLE user_account (
	user_id int NOT NULL,
	account_id int NOT NULL
);

CREATE TABLE transaction(
	transaction_id int NOT NULL auto_increment,
	from_account_id	int,
	to_account_id int,
	amount double ,
	transaction_dt	date,
	account_type_id int,
	description	varchar(200),
	CONSTRAINT pk_transaction PRIMARY KEY(transaction_id)
);

CREATE TABLE transaction_tye(
	id int NOT NULL auto_increment,
	description varchar(50),
	CONSTRAINT pk_transaction_type PRIMARY KEY(id)
);

CREATE TABLE account_type (
	id int NOT NULL auto_increment,
	description varchar(50),
	CONSTRAINT pk_account_type PRIMARY KEY(id)
);