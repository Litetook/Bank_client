package com.pragmatic.dao;


import com.pragmatic.model.Transaction;
import com.pragmatic.sql.SqlQuery;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionDao {
    Optional<Transaction> findTransactionById(Long transactionId);
    List<Transaction> findTransactionsByDateRange(Long accountId, Date dateFrom, Date dateTo);
    //List<Transaction> findAll();
    Transaction save(Transaction transaction);
}
