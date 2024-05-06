package main.java.com.pragmatic.repository;

import main.java.com.pragmatic.dao.AccountConverter;
import main.java.com.pragmatic.dao.FileDataProvider;
import main.java.com.pragmatic.model.Account;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AccountRepository implements IRepository {
    private static String tableImportName = "accounts.csv";
    private Integer lastId = 0;
    private Map<Integer, Account> accounts;
    private FileDataProvider file= new FileDataProvider(tableImportName);


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

    @Override
    public void updateFile() throws IOException {
        String data = AccountConverter.accountFileDataCreator(this.getRepoList());
        FileWriter writer = new FileWriter(this.file.getFileObj());
        writer.write(data);
        writer.close();
        System.out.println(this.file.getFileObj().getName()  + " "+ "successfully updated");
    }

    public Account createAccount(Integer currencyId, Integer userId, UserRepository userRepo) throws IOException {
        this.accounts.values().stream().forEach(account -> {
            if (account.getUserId().equals(userId) & account.getCurrencyId().equals(currencyId)) {
                throw new IllegalStateException("create new account issue: Account already exists. Userid:" + userId
                        + " currencyId:" +currencyId );
            }
        });
        Integer id =  ++this.lastId;
//        TODO. НАПРИКЛАД ТУТ ПУТАЮСЬ, ВИКЛИКАВ МЕТОД НАПРЯМУ, А НЕ ЧЕРЕЗ РЕПОЗИТОРІЙ, що було б правильно.
//         Потім шукаю помилку, чого файл не оновлюється, як мені треба. А потім зрозумів, що це і метод, яким я акк створюю... Туплю.
        Account newAccount = new Account(currencyId, this.lastId, userId);
        this.accounts.put(id, newAccount);
        userRepo.getUserById(userId).addAccount(newAccount);
        this.getRepoList().stream().forEach(account -> System.out.println(account));
        this.updateFile();

        return newAccount;
    }


}
