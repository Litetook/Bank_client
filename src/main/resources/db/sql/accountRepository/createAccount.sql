INSERT INTO accounts (account_id, user_id, currency_id, balance)
VALUES
(NEXTVAL('account_sequence'), :userId, :, :balance )
RETURNING account_id;