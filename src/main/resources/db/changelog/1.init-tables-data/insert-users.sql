--liquibase formatted sql
--changeset vitalii.tsomyk:users table
--comment insert users


INSERT INTO users (userid, name, email, password)
VALUES
    (nextval('user_sequence'), 'John Doe', 'johndoe@example.com', 'password123'),
    (nextval('user_sequence'), 'Jane Smith', 'janesmith@example.com', 'securePass!'),
    (nextval('user_sequence'), 'Alice Johnson', 'alicej@example.com', 'alice@2021'),
    (nextval('user_sequence'), 'Bob Brown', 'bobbrown@example.com', 'bobPass456'),
    (nextval('user_sequence'), 'Charlie White', 'charliew@example.com', 'charlie1234');