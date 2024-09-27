SELECT * FROM TRANSACTIONS
WHERE
(source_account_id = :accountId or destination_account_id=:accountId)
and ACTION_DATE >= :dateFrom
and ACTION_DATE < :dateTo;