# mysql -u root -p < CreateDBSQL.sql

DROP DATABASE budget;
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
	account_type_id INTEGER NOT NULL,
	name varchar(100),
	institution_name varchar(100),
	nickname varchar(100),
	balance double ,
	user_id INTEGER,
	CONSTRAINT pk_account PRIMARY KEY(id)
);

CREATE TABLE transaction(
	transaction_id INTEGER NOT NULL auto_increment,
	from_account_id	INTEGER,
	to_account_id INTEGER,
	memo	varchar(200),
	transaction_type varchar(100),
	transaction_dt date,
	amount double,
	CONSTRAINT pk_transaction PRIMARY KEY(transaction_id)
);

CREATE TABLE account_type (
	id INTEGER NOT NULL auto_increment,
	description varchar(50),
	CONSTRAINT pk_account_type PRIMARY KEY(id)
);

INSERT INTO account (account_type_id, name, institution_name, nickname, balance, user_id)
VALUES (1, "Joe's Checking Account", "BOA", "Primary Checking", 2073.98, 1),
			 (1, "Sean's Account", "Cap one", "My account with Cap One", 12.23, 2),
			 (2, "Mark's Big Money", "Bank of Moscow", "my Savings", 1000009.05, 3),
			 (1, "David's Checking", "Chase", "Checking", 1003.01, 4),
			 (2, "David's Savings", "Chase", "Savings", 1045.12, 4);

INSERT INTO user (first_name, last_name)
VALUES ("Joe", "Fen"), ("Sean", "Rowan"), ("Mark", "Moll"), ("David", "Tamulavage");

INSERT INTO account_type (description)
VALUES ("Checking"), ("Saving");

#INSERT INTO transaction_type (description)
#VALUES ("Rent"),("Internet/ Cellular"),("Loans"),("Credit Payment"),("Insurance"),("Utilities"),("Gas"),("Groceries"),("DayCare"),("Entertainment"),("CarPayment"),("Gym"),("Subscriptions");

INSERT INTO transaction (from_account_id, to_account_id, memo, transaction_type, transaction_dt, amount)
VALUES (1,null,"bills","Rent", "2018-01-07", 340.96),
			 (null,1,"work","Income", "2018-01-07", 1400.42),
			 (1,null,"bills","Internet/ Cellular", "2018-01-08", 43.64),
			 (1,null,"bills","Loans", "2018-01-09", 100.76),
			 (1,null,"bills","Credit Payment", "2018-01-10", 45.50),
			 (1,null,"bills","Insurance", "2018-01-11", 35.56),
			 (1,null,"stuff","Entertainment", "2018-01-12", 135.56),
			 (1,4,"stuff","Entertainment", "2018-01-12", 135.56),
			 (null,1,"work","Income", "2018-01-07", 1400.42),
			 (1,null,"bills","Rent", "2018-02-07", 340.96),
			 (1,null,"bills","Internet/ Cellular", "2018-02-08", 43.64),
			 (1,null,"bills","Loans", "2018-02-09", 100.76),
			 (1,null,"bills","Credit Payment", "2018-02-10", 45.50),
			 (1,null,"bills","Insurance", "2018-02-11", 35.56),
			 (1,null,"stuff","Subscriptions", "2018-01-12", 5.91),
			 (null,2,"work","Income", "2019-01-07", 3400.96),
			 (2,null,"bills","Rent", "2019-01-07", 340.96),
			 (2,null,"bills","Internet/ Cellular", "2019-01-08", 43.64),
			 (2,null,"bills","Loans", "2019-01-09", 100.76),
			 (2,null,"bills","Credit Payment", "2019-01-10", 45.50),
			 (2,null,"bills","Insurance", "2019-01-11", 35.56),
			 (2,null,"stuff","Entertainment", "2019-01-12", 135.56),
			 (2,4,"stuff","Entertainment", "2019-01-12", 135.56),
			 (null,2,"work","Income", "2019-02-07", 3400.96),
			 (2,null,"bills","Rent", "2019-02-07", 340.96),
			 (2,null,"bills","Internet/ Cellular", "2019-02-08", 43.64),
			 (2,null,"bills","Loans", "2019-02-09", 100.76),
			 (2,null,"bills","Credit Payment", "2019-02-10", 45.50),
			 (2,null,"bills","Insurance", "2019-02-11", 35.56),
			 (2,null,"stuff","Subscriptions", "2019-01-12", 5.91),
			 (null,3,"deposit","Income", "2019-03-12", 500000.91),
			 (null,3,"deposit","Income", "2019-03-13", 500000.91),
			 (null,3,"deposit","Income", "2019-03-14", 500000.91),
			 (3,5,"thanks Dave","Tip", "2019-03-15", 100000.00),
			 (4,5,"puting into savings","Savings", "2019-03-16", 300.00),
			 (4,5,"puting into savings","Savings", "2019-04-1", 300.00),
			 (4,null,"bills","Rent", "2019-04-2", 100.00);



