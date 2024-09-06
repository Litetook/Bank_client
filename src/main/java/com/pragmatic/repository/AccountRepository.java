package com.pragmatic.repository;

import com.pragmatic.dto.AccountDto;
import com.pragmatic.model.Account;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
@Log4j2
@Getter @Setter
public class AccountRepository implements IAccountRepository {
    private AtomicInteger lastId = new AtomicInteger(0);
    private Map<Integer, Account> accountMap = new HashMap<>();
    private UserRepository userRepo;

    public AccountRepository( UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    private  void initAccounts() {
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
        return new ArrayList<>(this.accountMap
                .values());
    }

    public Optional<Account> getAccountById(Integer id) {
        return Optional.ofNullable(this.accountMap.get(id));
    }

    public Account createAccount(Integer currencyId, Integer userId) {
        this.accountMap.values().forEach(account -> {
            if (userId.equals(account.getUserId()) &&   currencyId.equals(account.getCurrencyId()) ) {
                throw new IllegalStateException("create new account issue: Account already exists. Userid:" + userId
                        + " currencyId:" +currencyId );
            }
        });
        Integer id =  this.lastId.incrementAndGet();
        Account newAccount = new Account(currencyId, id, userId);
        this.accountMap.put(id, newAccount);
        this.userRepo.getUserById(userId).addAccount(newAccount);
        log.info(String.format("Accountid %d created", id));
        return newAccount;
    }

    public Account createAccount(AccountDto accountDto) {
        int id =this.lastId.incrementAndGet();
        Account newAccount = new Account(
                accountDto.getCurrencyId(),
                id,
                accountDto.getUserId()
                );
        log.info(String.format("acc is created from Dto. id is %d", id));
        this.accountMap.put(id, newAccount);
        this.userRepo.getUserById(accountDto.getUserId()).addAccount(newAccount);
        return newAccount;
    }


}
