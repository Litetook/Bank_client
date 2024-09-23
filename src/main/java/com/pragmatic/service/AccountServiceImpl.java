package com.pragmatic.service;

import com.pragmatic.dto.AccountDto;
import com.pragmatic.repository.AccountRepository;
import com.pragmatic.repository.AccountRepositoryImpl;
import com.pragmatic.repository.TransactionRepositoryImpl;
import com.pragmatic.model.Account;
import com.pragmatic.model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Log4j2
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepositoryImpl accountRepository;

    @Autowired
    TransactionRepositoryImpl transactions;

    public AccountServiceImpl(AccountRepositoryImpl accountRepository, TransactionRepositoryImpl transactionRepo) {
        this.transactions = transactionRepo;
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> getAccountById(Integer id) {
        return accountRepository.findById(id);
    }


//    public Iterable<Account> findAll() {
//        return accountRepository.findAll();
//    }

//    TODO переробити бо це шляпа
    public AccountDto createAccFromDto (AccountDto inputDto){
        Account builder = Account.builder().build();
        builder.setUserId(inputDto.getUserId())
                .setCurrencyId(inputDto.getCurrencyId())
                .setBalance(inputDto.getBalance());
        return new AccountDto(this.accountRepository.save(builder));
    }



    public Transaction moneyTransfer(Account accountFrom, Account accountTo, Double amount) {
        if (accountFrom.getBalance() >= amount) {
            accountFrom.setBalance(accountFrom.getBalance() - amount);
            accountTo.setBalance(accountTo.getBalance() + amount);
            Transaction transferTransaction = this.transactions.createNewTransaction(accountFrom.getId(), accountTo.getId(), amount);
            System.out.println("Success money transfer");
            return transferTransaction;
        } else {
            throw new RuntimeException("Error. Acc" + accountFrom.getId() + " from user " + accountFrom.getUserId() + " does not have enough balance to make transaction");
        }

    }

//    @Override
//    public Account updateAccount(Account account, Integer id) {
//        //TODO
//         this.accountRepository.findById(id).orElseThrow(() -> new NullPointerException(String.format("there is no account with id %d", id)));
//
//
//         ;
//    }

    @Override
    public void deleteAccount(Integer id) {
        //TODO
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accountList = new ArrayList<>();
//        this.accountRepository.findAll().forEach(accountList::add);
        return accountList;
    }


//    public Optional<Account> findAccountsByUserAndAccId(Integer userId, Integer currencyId) {
//        return StreamSupport.stream(accountRepository.findAll().spliterator(), false)
//                .filter(account -> account.getUserId() == userId)
//                .filter(account -> account.getCurrencyId() == currencyId)
//                .findFirst();
//    }


}


