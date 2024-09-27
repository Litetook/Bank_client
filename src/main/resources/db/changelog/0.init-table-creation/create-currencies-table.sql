--liquibase formatted sql
--changeset vitalii.tsomyk:accounts_table
--comment Initial changeset for accounts

CREATE TABLE IF NOT EXISTS currencies (
    currency_id INTEGER PRIMARY KEY,
    symbol varchar(10) not null
);

CREATE SEQUENCE currency_sequence START WITH 1 INCREMENT BY 1;

