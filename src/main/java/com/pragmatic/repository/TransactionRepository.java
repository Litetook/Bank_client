package com.pragmatic.repository;

import com.pragmatic.dao.FileDataProvider;
import com.pragmatic.dao.TransactionConverter;
import com.pragmatic.model.Transaction;
import org.springframework.stereotype.Repository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Repository
public class TransactionRepository implements ITransactionRepository{
    private Integer lastId = 0;
    private Map<Integer, Transaction> transactions;


    public TransactionRepository() {
        this.transactions = new HashMap<>();
    }

    public List<Transaction> getRepoList() {
        return this.transactions.values().stream().toList();
    }

    public Transaction getTransactionById(Integer transactionId) {
        return this.transactions.get(transactionId);
    }


    public Transaction createNewTransaction(Integer accountFromId, Integer accountToId, Double amount) {
        Transaction newTransaction = new Transaction();
        newTransaction.setId(++this.lastId);
        newTransaction.setAmount(amount);
        newTransaction.setAccountFromId(accountFromId);
        newTransaction.setAccountToId(accountToId);
        transactions.put(this.lastId, newTransaction);
        return  newTransaction;
    }

    public List<Transaction> getAccountTransactionsByDateRange(Integer accountId, Integer timestampFrom, Integer timeStampTo) {
        System.out.println("initiated method to find transaction by date range");
        Date dateFromInstance = new Date(timestampFrom.longValue() * 1000); // перетворюємо секунди в мілісекунди
        Date dateToInstance = new Date(timeStampTo.longValue() * 1000); // перетворюємо секунди в мілісекунди

        List<Transaction> list = new ArrayList<>();
        for (Transaction transaction : transactions.values()) {
            if ((Objects.equals(transaction.getAccountToId(), accountId)
                    || transaction.getAccountFromId() == accountId)) {
                if ((transaction.getActionDate().after(dateFromInstance))) {
                    if ((transaction.getActionDate().before(dateToInstance))) {
                        list.add(transaction);
                    }
                }
            }
        }
        return list;
    }

}
