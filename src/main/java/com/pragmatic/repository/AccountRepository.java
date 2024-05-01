package main.java.com.pragmatic.repository;

import main.java.com.pragmatic.dao.FileDataProvider;
import main.java.com.pragmatic.model.Account;
import main.java.com.pragmatic.model.User;

import java.util.*;

public class AccountRepository extends BaseRepository  implements IRepository {
    private static final String tableImportName = "accounts.csv";
    private FileDataProvider file= new FileDataProvider(tableImportName);
    private Integer lastId = 0;
    private Map<Integer, Account> accounts;
    private List<String> csvSchema = new ArrayList<>(Arrays.asList("id", "name", "email","password"));


    public AccountRepository( UserRepository userRepo) {
        super();
        this.accounts = new HashMap<>();
        List<Account> accountList = new FileDataProvider(tableImportName).initAccountData();
        accountList.forEach(account -> {
            this.accounts.put(account.getAccountid(), account);
            this.lastId ++;

//            Пишу так, бо не для кожного юзера може бути акк, тому нам простіше прогнати акки по списку юзерів, чим навпаки.
            userRepo.getUserById(account.getUserId()).addAccount(account);
        });
    }
    public List getRepoList() {
        List<Account> accountList = new ArrayList<Account>();
        this.accounts.values().stream().forEach(account -> {
            accountList.add(account);
        });

        return  accountList;
    }

    public Account getAccountById(Integer id) {
        return this.accounts.get(id);
    }


}
