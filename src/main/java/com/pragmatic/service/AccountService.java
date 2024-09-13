package com.pragmatic.service;

import com.pragmatic.model.Account;
import com.pragmatic.model.Transaction;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface AccountService {
    Optional<Account> getAccountById(Integer id);
//    String getAccount(Integer id);
    Transaction moneyTransfer(Account accountFrom, Account accountTo, Double amount) throws IOException;
    Account updateAccount(Account account, Integer id);
    void  deleteAccount(Integer id);
    List<Account> getAllAccounts();
}
