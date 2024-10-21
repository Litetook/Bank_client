INSERT INTO accounts (account_id, user_id, currency_id, balance)
VALUES
    (nextval('account_sequence'), 1, 1, 100.0),
    (nextval('account_sequence'), 1, 2, 100.0),
    (nextval('account_sequence'), 2, 1, 100.0);

