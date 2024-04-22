package main.java.com.pragmatic.repository;

import main.java.com.pragmatic.dao.FileDataProvider;
import main.java.com.pragmatic.dao.TransactionConverter;
import main.java.com.pragmatic.model.Transaction;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionRepository implements IRepository{
    private static String tableImportName = "transactions.csv";
    private Integer lastId = 0;
    private Map<Integer, Transaction> transactions;


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

    public List<Transaction> getAccountTransactionsByDateRange(Integer accountId, String dateFrom, String dateTo) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateFromInstance = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate dateToInstance = LocalDate.parse(dateTo, dateFormatter);

        return transactions.values().stream()
                .filter(transaction -> (transaction.getAccountToId() == accountId
                        || transaction.getAccountFromId() == accountId))
                .filter(transaction -> (transaction.getActionDate().isAfter(dateFromInstance)))
                .filter(transaction -> (transaction.getActionDate().isBefore(dateToInstance)))
                .collect(Collectors.toList());

    }
}
