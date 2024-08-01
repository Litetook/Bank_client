package com.pragmatic.service;

import com.pragmatic.repository.AccountRepository;
import com.pragmatic.repository.TransactionRepository;
import com.pragmatic.model.Account;
import com.pragmatic.model.Transaction;

import java.io.IOException;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;


@Service
public class AccountService {

    AccountRepository accounts;
    TransactionRepository transactions;

     AccountService(AccountRepository accountRepo, TransactionRepository transactionRepo ) {
        this.transactions = transactionRepo;
        this.accounts = accountRepo;
    }

//    TODO add currency converter

    public String getAccount(Integer id) {
         return  "test "+id;
    }

    public  Transaction moneyTransfer(Account accountFrom, Account accountTo, Double amount ) throws IOException {
        if (accountFrom.getBalance() >= amount ) {
            accountFrom.setBalance(accountFrom.getBalance() - amount);
            accountTo.setBalance(accountTo.getBalance() + amount);
            Transaction transferTransaction = this.transactions.createNewTransaction(accountFrom.getId(), accountTo.getId(), amount);
//            this.accounts.updateFile();
            System.out.println("Success money transfer");
            return  transferTransaction;
        }

        else {
            throw new RuntimeException("Error. Acc" + accountFrom.getId() + " from user " +  accountFrom.getUserId() + " does not have enough balance to make transaction");
        }


    }
}
