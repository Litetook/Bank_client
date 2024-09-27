package com.pragmatic.dao;


import com.pragmatic.model.Transaction;
import com.pragmatic.sql.SqlQuery;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionDao {
    Optional<Transaction> findTransactionById(Integer transactionId);
    List<Transaction> findTransactionsByDateRange(Integer accountid, Date dateFrom, Date dateTo);
//    Transaction createTransaction(Integer destinationAccountId, Integer sourceAccountId, Double amount);
    List<Transaction> findAll();
    Integer insert(SqlQuery sqlQuery);
    Transaction save(Transaction transaction);

}
