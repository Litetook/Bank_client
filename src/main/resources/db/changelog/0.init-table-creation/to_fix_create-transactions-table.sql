--liquibase formatted sql
--changeset vitalii.tsomyk:accounts_table
--comment Initial changeset for accounts

CREATE TABLE IF NOT EXISTS transactions (
    transactionid INTEGER PRIMARY KEY,
    accountfromid integer not null,
    accounttoid integer not null,
    amount decimal(10,2) not null,
    action_date timestamp not null
);

CREATE SEQUENCE transaction_sequence START WITH 1 INCREMENT BY 1;

