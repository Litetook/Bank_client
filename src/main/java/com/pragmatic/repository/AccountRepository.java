package com.pragmatic.repository;

import com.pragmatic.model.Account;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class AccountRepository implements IAccountRepository {
    private AtomicInteger lastId = new AtomicInteger(0);;
    private Map<Integer, Account> accountMap;
    private  UserRepository userRepo;

    public AccountRepository( UserRepository userRepo) throws IOException {
        this.userRepo = userRepo;
        this.accountMap = new HashMap<>();

        Integer[][] accounts = {
                {1,1},
                {1,2},
                {1,3},
                {2,1},
                {3,1},
                {4,2},
        };


        for (Integer[] i: accounts) {
            Account newAcc = createAccount(i[1], i[0]);

            this.accountMap.put(newAcc.getId(), newAcc);
        }

    }


    public List<Account> getRepoList() {
        return  this.accountMap
                .values()
                .stream()
                .collect(Collectors.toList());
    }

    public Optional<Account> getAccountById(Integer id) {
        return Optional.ofNullable(this.accountMap.get(id));
    }

    public Account createAccount(Integer currencyId, Integer userId) {
        this.accountMap.values().forEach(account -> {
            if (account.getUserId().equals(userId) & account.getCurrencyId().equals(currencyId)) {
                throw new IllegalStateException("create new account issue: Account already exists. Userid:" + userId
                        + " currencyId:" +currencyId );
            }
        });
        Integer id =  this.lastId.incrementAndGet();
        Account newAccount = new Account(currencyId, id, userId);
        this.accountMap.put(id, newAccount);
        this.userRepo.getUserById(userId).addAccount(newAccount);
        this.getRepoList().forEach(account -> System.out.println(account));

        return newAccount;
    }


}
