--liquibase formatted sql
--changeset vitalii.tsomyk:accounts_table
--comment Initial changeset for accounts

CREATE TABLE IF NOT EXISTS transactions (
    transaction_id INTEGER PRIMARY KEY,
    source_account_id integer not null,
    destination_account_id integer not null,
    amount decimal(10,2) not null,
    action_date timestamp not null
);

CREATE SEQUENCE transaction_sequence START WITH 1 INCREMENT BY 1;

