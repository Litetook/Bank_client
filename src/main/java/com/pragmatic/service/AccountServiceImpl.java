package com.pragmatic.service;

import com.pragmatic.dto.AccountDto;
import com.pragmatic.repository.AccountRepository;
import com.pragmatic.repository.ITransactionRepository;
import com.pragmatic.repository.TransactionRepository;
import com.pragmatic.model.Account;
import com.pragmatic.model.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@Log4j2
@Service
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;
    TransactionRepository transactions;

     public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepo) {
        this.transactions = transactionRepo;
        this.accountRepository = accountRepository;
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

    public Optional<Account> findAccountsByUserAndAccId(int userId, int currencyId) {
         log.debug("repository account list");
         log.info(accountRepository.getRepoList());
        return Optional.ofNullable(accountRepository.getRepoList().stream()
                .filter(repoAcc -> repoAcc.getCurrencyId() == currencyId && repoAcc.getUserId() == userId)
                .findFirst()
                .orElse(null));

     }

    public AccountDto createAccFromAccDto(AccountDto inputDto) {
        AccountDto responseAccDto = new AccountDto(this.accountRepository.createAccount(inputDto));
        return responseAccDto;
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


