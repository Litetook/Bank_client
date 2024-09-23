--liquibase formatted sql
--changeset vitalii.tsomyk:accounts_table
--comment Initial changeset for accounts

INSERT INTO accounts (accountid, userid, currencyid, balance)
VALUES
    (nextval('account_sequence'), 1, 1, 0.0),
    (nextval('account_sequence'), 2, 1, 0.0),
    (nextval('account_sequence'), 3, 1, 0.0),
    (nextval('account_sequence'), 4, 1, 0.0),
    (nextval('account_sequence'), 5, 1, 0.0);