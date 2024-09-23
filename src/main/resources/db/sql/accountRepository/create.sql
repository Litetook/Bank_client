INSERT INTO accounts (accountid, userid, currencyid, balance)
values
(nextval('account_sequence'), :userid, :currencyid, :balance )
RETURNING accountid;