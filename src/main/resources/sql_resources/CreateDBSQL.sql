# mysql -u root -p < CreateDBSQL.sql

DROP DATABASE if exists budget;
CREATE DATABASE budget;

USE budget;

CREATE TABLE user
(
  user_id    INTEGER     NOT NULL auto_increment,
  first_name varchar(50) NOT NULL,
  last_name  varchar(50) NOT NULL,
  username   varchar(50) NOT NULL,
  CONSTRAINT pk_user PRIMARY KEY (user_id)
);

CREATE TABLE account
(
  id               INTEGER NOT NULL auto_increment,
  account_type_id  INTEGER NOT NULL,
  name             varchar(100),
  institution_name varchar(100),
  nickname         varchar(100),
  balance          double,
  user_id          INTEGER,
  CONSTRAINT pk_account PRIMARY KEY (id)
);

CREATE TABLE transaction_type
(
  id          INTEGER NOT NULL auto_increment,
  description varchar(50),
  CONSTRAINT pk_transaction_type PRIMARY KEY (id)
);

CREATE TABLE transaction
(
  transaction_id   INTEGER NOT NULL auto_increment,
  from_account_id  INTEGER,
  to_account_id    INTEGER,
  memo             varchar(200),
  transaction_type INTEGER,
  transaction_dt   date,
  amount           double,
  CONSTRAINT pk_transaction PRIMARY KEY (transaction_id),
  CONSTRAINT fk_transaction_type FOREIGN KEY (transaction_type) REFERENCES transaction_type (id)
);


CREATE TABLE account_type
(
  id          INTEGER NOT NULL auto_increment,
  description varchar(50),
  CONSTRAINT pk_account_type PRIMARY KEY (id)
);

INSERT INTO account (account_type_id, name, institution_name, nickname, balance, user_id)
VALUES (1, "Joe's Checking Account", "BOA", "Primary Checking", 2073.98, 1),
       (1, "Sean's Account", "Cap one", "My account with Cap One", 12.23, 2),
       (2, "Mark's Big Money", "Bank of Moscow", "my Savings", 1000009.05, 3),
       (1, "David's Checking", "Chase", "Checking", 1003.01, 4),
       (2, "David's Savings", "Chase", "Savings", 1045.12, 4);

INSERT INTO user (first_name, last_name, username)
VALUES ("Joe", "Fen", "Fenniless"),
       ("Sean", "Rowan", "SpringKing"),
       ("Mark", "Moll", "DarthMoll"),
       ("David", "Tamulavage", "EvilDave");

INSERT INTO account_type (description)
VALUES ("Checking"),
       ("Saving");

INSERT INTO transaction_type (description)
VALUES ("Rent"),
       ("Internet/ Cellular"),
       ("Loans"),
       ("Credit Payment"),
       ("Insurance"),
       ("Utilities"),
       ("Gas"),
       ("Groceries"),
       ("DayCare"),
       ("Entertainment"),
       ("Income"),
       ("Other"),
       ("Subscriptions");

INSERT INTO transaction (from_account_id, to_account_id, memo, transaction_type, transaction_dt, amount)
VALUES (1, null, "bills", 1, "2018-01-07", 340.96),
       (null, 1, "work", 11, "2018-01-07", 1400.42),
       (1, null, "bills", 2, "2018-01-08", 43.64),
       (1, null, "bills", 3, "2018-01-09", 100.76),
       (1, null, "bills", 4, "2018-01-10", 45.50),
       (1, null, "bills", 5, "2018-01-11", 35.56),
       (1, null, "stuff", 10, "2018-01-12", 135.56),
       (1, 4, "stuff", 10, "2018-01-12", 135.56),
       (null, 1, "work", 11, "2018-01-07", 1400.42),
       (1, null, "bills", 1, "2018-02-07", 340.96),
       (1, null, "bills", 2, "2018-02-08", 43.64),
       (1, null, "bills", 3, "2018-02-09", 100.76),
       (1, null, "bills", 4, "2018-02-10", 45.50),
       (1, null, "bills", 5, "2018-02-11", 35.56),
       (1, null, "stuff", 13, "2018-01-12", 5.91),
       (null, 2, "work", 11, "2019-01-07", 3400.96),
       (2, null, "bills", 1, "2019-01-07", 340.96),
       (2, null, "bills", 2, "2019-01-08", 43.64),
       (2, null, "bills", 3, "2019-01-09", 100.76),
       (2, null, "bills", 4, "2019-01-10", 45.50),
       (2, null, "bills", 5, "2019-01-11", 35.56),
       (2, null, "stuff", 10, "2019-01-12", 135.56),
       (2, 4, "stuff", 10, "2019-01-12", 135.56),
       (null, 2, "work", 11, "2019-02-07", 3400.96),
       (2, null, "bills", 1, "2019-02-07", 340.96),
       (2, null, "bills", 2, "2019-02-08", 43.64),
       (2, null, "bills", 3, "2019-02-09", 100.76),
       (2, null, "bills", 4, "2019-02-10", 45.50),
       (2, null, "bills", 5, "2019-02-11", 35.56),
       (2, null, "stuff", 13, "2019-01-12", 5.91),
       (null, 3, "deposit", 11, "2019-03-12", 500000.91),
       (null, 3, "deposit", 11, "2019-03-13", 500000.91),
       (null, 3, "deposit", 11, "2019-03-14", 500000.91),
       (3, 5, "thanks Dave", 3, "2019-03-15", 100000.00),
       (4, 5, "puting into savings", 12, "2019-03-16", 300.00),
       (4, 5, "puting into savings", 12, "2019-04-1", 300.00),
       (4, null, "bills", 1, "2019-04-2", 100.00);



