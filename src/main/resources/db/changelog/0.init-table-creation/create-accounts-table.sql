--liquibase formatted sql
--changeset vitalii.tsomyk:accounts_table
--comment Initial changeset for accounts

CREATE TABLE IF NOT EXISTS accounts (
    accountid INTEGER PRIMARY KEY,
    userid integer not null,
    currencyid integer not null,
    balance decimal(10,2) not null
);

CREATE SEQUENCE account_sequence START WITH 1 INCREMENT BY 1;

--add reference here to userid
