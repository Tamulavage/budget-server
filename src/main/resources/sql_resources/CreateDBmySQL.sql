# mysql -u root -p < CreateDBmySQL.sql

DROP DATABASE if exists budget;
CREATE DATABASE budget;

USE budget;

CREATE TABLE profile
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
  nick_name        varchar(100),
  institution_name varchar(100),
  balance          double,
  active           varchar(50),
  CONSTRAINT pk_account PRIMARY KEY (id)
);

CREATE TABLE profile_account_xref
(
  profile_id INTEGER NOT NULL,
  account_id INTEGER NOT NULL,
  primary_profile_id INTEGER NOT NULL,  
  CONSTRAINT fk_profile_id FOREIGN KEY (profile_id) REFERENCES profile (user_id),
  CONSTRAINT fk_account_id FOREIGN KEY (account_id) REFERENCES account (id)
);

CREATE TABLE transaction
(
  transaction_id   INTEGER NOT NULL auto_increment,
  from_account_id  INTEGER,
  to_account_id    INTEGER,
  memo             varchar(200),
  transaction_dt   date,
  amount           double,
  CONSTRAINT pk_transaction PRIMARY KEY (transaction_id)
);

CREATE TABLE account_type
(
  id          INTEGER NOT NULL auto_increment,
  description varchar(50),
  CONSTRAINT pk_account_type PRIMARY KEY (id)
);

CREATE TABLE future_accounting_org (
  org_id     INTEGER NOT NULL auto_increment,
  direction  VARCHAR(5),
  org_name   VARCHAR(25),
  profile_id INTEGER,
  CONSTRAINT pk_future_accounting_org PRIMARY KEY (org_id)  
);

CREATE TABLE future_accounting (
  org_id          INTEGER NOT NULL,
  month           INTEGER NOT NULL,
  amount          double NOT null,
  freq_per_month  INTEGER NOT NULL,
  CONSTRAINT pk_future_accounting PRIMARY KEY (org_id, month) ,
  CONSTRAINT fk_accounting FOREIGN KEY (org_id) REFERENCES future_accounting_org (org_id)
);

------------------
INSERT INTO account_type (description)
VALUES ("Checking"),
       ("Savings"),
       ("CD"),
       ("529b")
       ("401k");

---------------

INSERT INTO account (account_type_id, nick_name, institution_name,  balance)
VALUES (1, "Primary Checking", "Chase", 1003.01),
       (2, "Primary Savings", "WSFS", 1045.12);

INSERT INTO profile (first_name, last_name, username)
VALUES    ("David", "Tamulavage", "tamulavage");


INSERT INTO budget.profile_account_xref
 VALUES (1,2,0),
    (2,2,0),
    (2,1,0);

INSERT INTO budget.transaction (from_account_id, to_account_id, memo, transaction_dt, amount)
VALUES (1, null, "bills",  "2018-01-07", 340.96),
       (null, 1, "work", "2018-01-07", 1400.42),
       (1, 2, "transfer", "2018-01-08", 43.64),
       (1, null, "bills", "2018-01-09", 100.76);

INSERT INTO budget.future_accounting_org 
VALUES (1, "I", "Paycheck",2 ),
  (2, "O", "Rent" , 2),
  (3, "O", "CreditCard", 2);

INSERT INTO budget.future_accounting 
VALUES (1, 1, 2002.01, 2),
(1, 2, 2000.01, 2),
(1, 3, 2000.01, 2),
(1, 10, 2000.01, 2),
(2, 1, 2000.01, 1),
(2, 2, 1700.50, 1),
(2, 3, 1700.50, 1),
(2, 9, 1100.50, 1),
(3, 1, 500.50, 1),
(3, 2, 500.50, 1),
(3, 12, 1500.50, 1),
(3, 3, 100.50, 1);