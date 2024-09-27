SELECT * FROM TRANSACTIONS
WHERE
(source_account_id = :accountId or destination_account_id=:accountId)
and ACTION_DATE >= :datefrom
and ACTION_DATE < :dateto;