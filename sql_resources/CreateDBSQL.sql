# mysql -u root -p < CreateDBSQL.sql

#DROP DATABASE budget;
CREATE DATABASE budget;

USE budget;


CREATE TABLE user (
	 user_id INTEGER NOT NULL auto_increment,
	 first_name varchar(50) NOT NULL,
	 last_name varchar(50) NOT NULL,
	 CONSTRAINT pk_user PRIMARY KEY(user_id)
);

CREATE TABLE account (
	id INTEGER NOT NULL auto_increment,
	account_type_id LONG NOT NULL,
	name varchar(100),
	institution_name varchar(100),
	nickname varchar(100),
	balance double ,
	type_id INTEGER,
	user_id INTEGER,
	CONSTRAINT pk_account PRIMARY KEY(id)
);

CREATE TABLE user_account (
	user_id INTEGER NOT NULL,
	account_id INTEGER NOT NULL
);

CREATE TABLE transaction(
	transaction_id INTEGER NOT NULL auto_increment,
	from_account_id	INTEGER,
	to_account_id INTEGER,
	memo	varchar(200),
	transaction_type INTEGER ,
	transaction_dt	date,
	account_type_id INTEGER,
	amount double,
	CONSTRAINT pk_transaction PRIMARY KEY(transaction_id)
);

CREATE TABLE transaction_tye(
	id INTEGER NOT NULL auto_increment,
	description varchar(50),
	CONSTRAINT pk_transaction_type PRIMARY KEY(id)
);

CREATE TABLE account_type (
	id INTEGER NOT NULL auto_increment,
	description varchar(50),
	CONSTRAINT pk_account_type PRIMARY KEY(id)
);