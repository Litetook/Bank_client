package com.pragmatic.service;

import com.pragmatic.repository.AccountRepository;
import com.pragmatic.repository.TransactionRepository;
import com.pragmatic.model.Account;
import com.pragmatic.model.Transaction;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;
    TransactionRepository transactions;

     public AccountServiceImpl(AccountRepository accountRepo, TransactionRepository transactionRepo) {
        this.transactions = transactionRepo;
        this.accountRepository = accountRepo;
    }

    @Override
    public Optional<Account> getAccountById(Integer id) {
        return accountRepository.getAccountById(id);
    }

    @Override
    public String getAccount(Integer id) {
        return accountRepository.getAccountById(id).toString();
    }

    public List<Account> getAllAccounts() {
         return accountRepository.getRepoList();
    }

    public  Transaction moneyTransfer(Account accountFrom, Account accountTo, Double amount )  {
        if (accountFrom.getBalance() >= amount ) {
            accountFrom.setBalance(accountFrom.getBalance() - amount);
            accountTo.setBalance(accountTo.getBalance() + amount);
            Transaction transferTransaction = this.transactions.createNewTransaction(accountFrom.getId(), accountTo.getId(), amount);
            System.out.println("Success money transfer");
            return  transferTransaction;
        }

        else {
            throw new RuntimeException("Error. Acc" + accountFrom.getId() + " from user " +  accountFrom.getUserId() + " does not have enough balance to make transaction");
        }

    }



}


