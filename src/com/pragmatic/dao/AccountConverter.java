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
        account.setUserid(Integer.valueOf(values[1]));
        account.setCurrencyId(Integer.valueOf(values[2]));
        account.setBalance(Double.valueOf(values[3]));
        return account;
    }

    public static String accountFileDataCreator(List<Account> accountList) {
        StringBuilder string = new StringBuilder();
//       HEADER
        string.append(String.join(",", csvSchema));
//        System.out.printf("Header");
//        System.out.println(string);

        if (accountList.size() > 0) {
            string.append("\n");
        }
        for (int i = 0; i <= accountList.size() -1; i++) {
            Account account = accountList.get(i);
            string.append(String.join(",", account.getId().toString(),account.getUserId().toString(), account.getCurrencyId().toString(), account.getBalance().toString()));
            string.append("\n");
        }
        return  string.toString();

    }
    }