package main.java.com.pragmatic.repository;

import main.java.com.pragmatic.dao.FileDataProvider;
import main.java.com.pragmatic.model.Transaction;
import java.util.*;
import java.util.stream.Collectors;

public class TransactionRepository implements IRepository{
    private static String tableImportName = "transactions.csv";
    private Integer lastId = 0;
    private Map<Integer, Transaction> transactions;
    private List<String> csvSchema = new ArrayList<>(Arrays.asList("id", "name", "email","password"));


    public TransactionRepository() {
        this.transactions = new HashMap<>();
        List<Transaction> transactionList = new FileDataProvider(tableImportName).initTransactionData();

        transactionList.forEach( transaction -> {
            this.transactions.put(transaction.getId(), transaction);
        });
    }

    public Map<Integer, Transaction> getRepoList() {
        return this.transactions;
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
