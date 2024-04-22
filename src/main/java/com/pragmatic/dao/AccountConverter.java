package main.java.com.pragmatic.dao;

import main.java.com.pragmatic.model.Account;
import main.java.com.pragmatic.repository.CurrencyRepository;

public class AccountConverter {
    public static Account parseAccount(String data ) {
        String[] values = data.split(",");

        if (values.length != 4) {
            throw new IllegalArgumentException("Invalid acc parse data. Row number is not correct. Line: " + data);
        }

        Account account = new Account();
        account.setAccountid(Integer.valueOf(values[0]));
        account.setUserid(Integer.valueOf(values[1]));
        account.setCurrencyId(Integer.valueOf(values[2]));
        account.setBalance(Double.valueOf(values[3]));
        return account;
    }
    }