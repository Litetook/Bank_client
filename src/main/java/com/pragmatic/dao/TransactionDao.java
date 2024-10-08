package com.pragmatic.dao;


import com.pragmatic.model.Transaction;
import com.pragmatic.sql.SqlQuery;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionDao {
    Optional<Transaction> findTransactionById(Integer transactionId);
    List<Transaction> findTransactionsByDateRange(Integer accountId, Date dateFrom, Date dateTo);
    List<Transaction> findAll();
    Integer insert(SqlQuery sqlQuery);
    Transaction save(Transaction transaction);
}
