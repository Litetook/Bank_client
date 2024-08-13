package com.pragmatic.repository;

import com.pragmatic.dao.AccountConverter;
import com.pragmatic.dao.FileDataProvider;
import com.pragmatic.model.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class AccountRepository implements IAccountRepository {
    private Integer lastId = 0;
    private Map<Integer, Account> accounts;
    private  UserRepository userRepo;

    public AccountRepository( UserRepository userRepo) throws IOException {
        this.userRepo = userRepo;
        this.accounts = new HashMap<>();

        Integer[][] accounts = {
                {1,1},
                {1,2},
                {1,3},
                {2,1},
                {3,1},
                {4,2},
        };


        for (Integer[] i: accounts) {
            this.accounts.put(this.lastId, createAccount(i[1], i[0]));
        }

    }


    public List<Account> getRepoList() {
        return  this.accounts
                .values()
                .stream()
                .collect(Collectors.toList());
    }

    public Account getAccountById(Integer id) {
        return this.accounts.get(id);
    }

    public Account createAccount(Integer currencyId, Integer userId) {
        this.accounts.values().forEach(account -> {
            if (account.getUserId().equals(userId) & account.getCurrencyId().equals(currencyId)) {
                throw new IllegalStateException("create new account issue: Account already exists. Userid:" + userId
                        + " currencyId:" +currencyId );
            }
        });
        Integer id =  ++this.lastId;
        Account newAccount = new Account(currencyId, this.lastId, userId);
        this.accounts.put(id, newAccount);
        this.userRepo.getUserById(userId).addAccount(newAccount);
        this.getRepoList().forEach(account -> System.out.println(account));

        return newAccount;
    }

    @Override
    public Account addAccount(Account newAccount) {
        Integer id =  ++this.lastId;
        this.accounts.put(id, newAccount);
        newAccount.setId(id);
        System.out.println("new acc created with id " + newAccount.getId());

        return  newAccount;
    }
}
