package com.pragmatic.repository;

import com.pragmatic.model.Transaction;

import java.util.List;

public interface ITransactionRepository {
    List<Transaction> getRepoList();
    Transaction getTransactionById(Integer id);
    Transaction createNewTransaction(Integer accountFromId, Integer accountToId, Double Amount);

}
