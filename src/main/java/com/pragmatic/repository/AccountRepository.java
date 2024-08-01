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
public class AccountRepository implements IRepository {
    private Integer lastId = 0;
    private Map<Integer, Account> accounts;
//    private FileDataProvider file= new FileDataProvider(tableImportName);
//    private static String tableImportName = "accounts.csv";

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


//        List<Account> accountList = new FileDataProvider(tableImportName).initAccountData();
//        accountList.forEach(account -> {
//            if (!this.accounts.containsKey(account.getId())) {
//                this.accounts.put(account.getId(), account);
//                if (lastId < account.getId() ) {
//                    lastId = account.getId();
//                }
//            }
//            else {
//                throw new  IllegalStateException(account.getId() +  "accId already exists in repo");
//            }

//            Пишу так, бо не для кожного юзера може бути акк, тому нам простіше прогнати акки по списку юзерів, чим навпаки.
//            this.userRepo.getUserById(account.getUserId()).addAccount(account);
//        });
    }


    public List<Account> getRepoList() {
        return  this.accounts.values().stream().collect(Collectors.toList());
    }

    public Account getAccountById(Integer id) {
        return this.accounts.get(id);
    }

//    public void updateFile() throws IOException {
//        String data = AccountConverter.accountFileDataCreator(this.getRepoList());
//        FileWriter writer = new FileWriter(this.file.getFileObj());
//        writer.write(data);
//        writer.close();
//        System.out.println(this.file.getFileObj().getName()  + " "+ "successfully updated");
//    }

    public Account createAccount(Integer currencyId, Integer userId) throws IOException {
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
//        this.updateFile();

        return newAccount;
    }


}
