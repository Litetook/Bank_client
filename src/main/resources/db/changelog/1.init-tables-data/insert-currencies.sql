--liquibase formatted sql
--changeset vitalii.tsomyk:currencies_table
--comment insert currencies

INSERT INTO currencies (currencyid, symbol)
VALUES
    (nextval('currency_sequence'), 'USD'),
    (nextval('currency_sequence'), 'IDR'),
    (nextval('currency_sequence'), 'KRW'),
    (nextval('currency_sequence'), 'PLN'),
    (nextval('currency_sequence'), 'UAH')

