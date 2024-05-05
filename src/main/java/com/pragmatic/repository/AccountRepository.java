package main.java.com.pragmatic.repository;

import main.java.com.pragmatic.dao.FileDataProvider;
import main.java.com.pragmatic.model.Account;

import java.util.*;

public class AccountRepository implements IRepository {
    private static String tableImportName = "accounts.csv";
    private Integer lastId = 0;
    private Map<Integer, Account> accounts;


    public AccountRepository( UserRepository userRepo) {
        this.accounts = new HashMap<>();
        List<Account> accountList = new FileDataProvider(tableImportName).initAccountData();
        accountList.forEach(account -> {
            if (!this.accounts.containsKey(account.getId())) {
                this.accounts.put(account.getId(), account);
                if (lastId < account.getId() ) {
                    lastId = account.getId();
                }
            }
            else {
                throw new  IllegalStateException(account.getId() +  "accId already exists in repo");
            }

//            Пишу так, бо не для кожного юзера може бути акк, тому нам простіше прогнати акки по списку юзерів, чим навпаки.
            userRepo.getUserById(account.getUserId()).addAccount(account);
        });
    }
    public List<Account> getRepoList() {
        return  this.accounts.values().stream().toList();
    }

    public Account getAccountById(Integer id) {
        return this.accounts.get(id);
    }


}
