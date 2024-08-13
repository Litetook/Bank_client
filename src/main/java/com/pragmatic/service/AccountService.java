package com.pragmatic.service;

import com.pragmatic.model.Account;
import com.pragmatic.model.Transaction;

import java.io.IOException;

public interface AccountService {
    String getAccount(Integer id);
    Transaction moneyTransfer(Account accountFrom, Account accountTo, Double amount) throws IOException;
}
