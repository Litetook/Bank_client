package main.java.com.pragmatic.repository;

import main.java.com.pragmatic.dao.FileDataProvider;
import main.java.com.pragmatic.dao.UserConverter;
import main.java.com.pragmatic.model.Transaction;
import main.java.com.pragmatic.model.User;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class TransactionRepository implements IRepository{
    private static String tableImportName = "transactions.csv";
    private Integer lastId = 0;
    private Map<Integer, Transaction> transactions;
    private FileDataProvider file= new FileDataProvider(tableImportName);


    public TransactionRepository() {
        this.transactions = new HashMap<>();
        List<Transaction> transactionList = new FileDataProvider(tableImportName).initTransactionData();

        transactionList.forEach( transaction -> {
            if (!this.transactions.containsKey(transaction.getId())) {
                this.transactions.put(transaction.getId(), transaction);
                if (this.lastId < transaction.getId()) {
                    this.lastId = transaction.getId();
                }
            }
            else {
                throw new IllegalStateException(transaction.getId() + "transactionid already exists in repo");
            }

        });
    }

    public List<Transaction> getRepoList() {
        return this.transactions.values().stream().toList();
    }


    public Transaction getTransactionById(Integer transactionId) {
        return this.transactions.get(transactionId);
    }

    public List<Transaction> getAccountTransactionsByDateRange(Integer accountId, Integer timestampFrom, Integer timeStampTo) {
        System.out.println("initiated method to find transaction by date range");
        Date dateFromInstance = new Date(timestampFrom.longValue() * 1000); // перетворюємо секунди в мілісекунди
        Date dateToInstance = new Date(timeStampTo.longValue() * 1000); // перетворюємо секунди в мілісекунди

        return transactions.values().stream()
                .filter(transaction -> (transaction.getAccountToId() == accountId
                        || transaction.getAccountFromId() == accountId))
                .filter(transaction -> (transaction.getActionDate().after(dateFromInstance)))
                .filter(transaction -> (transaction.getActionDate().before(dateToInstance)))
                .collect(Collectors.toList());

    }

}
