package com.pragmatic.dao;

import com.pragmatic.model.Account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountConverter {
    public static List<String> csvSchema = new ArrayList<>(Arrays.asList("id", "userid", "currencyid","balance"));

    public static Account parseAccount(String data ) {
        String[] values = data.split(",");

        if (values.length != 4) {
            throw new IllegalArgumentException("Invalid acc parse data. Row number is not correct. Line: " + data);
        }

        Account account = new Account();
        account.setId(Integer.valueOf(values[0]));
        account.setUserId(Integer.valueOf(values[1]));
        account.setCurrencyId(Integer.valueOf(values[2]));
        account.setBalance(Double.valueOf(values[3]));
        return account;
    }

    public static String accountFileDataCreator(List<Account> accountList) {
        StringBuilder string = new StringBuilder();
        string.append(String.join(",", csvSchema));

        if (accountList.size() > 0) {
            string.append("\n");
        }
        for (int i = 0; i <= accountList.size() -1; i++) {
            Account account = accountList.get(i);
            string.append(String.join(",", String.valueOf(account.getId()), String.valueOf(account.getUserId()), String.valueOf(account.getCurrencyId()), account.getBalance().toString()));
            string.append("\n");
        }
        return  string.toString();

    }
    }