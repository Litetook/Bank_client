package com.pragmatic.repository;

import com.pragmatic.dao.FileDataProvider;
import com.pragmatic.dao.TransactionConverter;
import com.pragmatic.model.Transaction;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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

    public Transaction createNewTransaction(Integer accountFromId, Integer accountToId, Double amount) throws IOException {
        Transaction newTransaction = new Transaction();
        newTransaction.setId(++this.lastId);
        newTransaction.setAmount(amount);
        newTransaction.setAccountFromId(accountFromId);
        newTransaction.setAccountToId(accountToId);
        transactions.put(this.lastId, newTransaction);
        this.updateFile();
        return  newTransaction;
    }

    @Override
    public void updateFile() throws IOException {
        String data = TransactionConverter.transactionFileDataCreator(this.getRepoList());
        FileWriter writer = new FileWriter(this.file.getFileObj());
        writer.write(data);
        writer.close();
        System.out.println(this.file.getFileObj().getName()  + " "+ "successfully updated");
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
