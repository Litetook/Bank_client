--liquibase formatted sql
--changeset vitalii.tsomyk:users_table
--comment Initial changeset for users

CREATE TABLE IF NOT EXISTS users (
    userid INTEGER PRIMARY KEY not null,
    name VARCHAR(50),
    email varchar(50) not null,
    password varchar(20) not null
);

CREATE SEQUENCE user_sequence START WITH 1 INCREMENT BY 1;