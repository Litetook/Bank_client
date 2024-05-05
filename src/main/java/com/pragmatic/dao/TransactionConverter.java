package main.java.com.pragmatic.dao;

import main.java.com.pragmatic.model.Account;
import main.java.com.pragmatic.model.Transaction;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TransactionConverter {
    private static List<String> csvSchema = new ArrayList<>(Arrays.asList("transactionId", "accountFromId", "accountToId","amount", "timestamp"));

    public static Transaction parseTransaction(String data) {
        String[] values = data.split(",");

        if (values.length != 5) {
            throw new IllegalArgumentException("Invalid user data. Number of csv rows is incorrect");
        }

        Transaction transaction = new Transaction();
        transaction.setId(Integer.valueOf(values[0]));
        transaction.setAccountFromId(Integer.valueOf(values[1]));
        transaction.setAccountToId(Integer.valueOf(values[2]));
        transaction.setAmount(Double.valueOf(values[3]));
        transaction.setActionDate(Date.from(Instant.ofEpochSecond(Integer.valueOf(values[4]))));

        return transaction;
    }

    public static String transactionFileDataCreator(List<Transaction> transactionList) {
        StringBuilder string = new StringBuilder();
//       HEADER
        string.append(String.join(",", csvSchema));
        if (transactionList.size() > 0) {
            string.append("\n");
        }
        for (int i = 0; i <= transactionList.size() -1; i++) {
            Transaction transaction = transactionList.get(i);
            string.append(String.join(",", transaction.getId().toString(),
                    transaction.getAccountFromId().toString(),
                    transaction.getAccountToId().toString(),
                    transaction.getAmount().toString(),
                    transaction.getActionDate().toString(),
                    "\n"));


        }
        return  string.toString();

    }

}
