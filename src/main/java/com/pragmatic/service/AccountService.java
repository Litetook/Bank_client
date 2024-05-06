package main.java.com.pragmatic.service;

import main.java.com.pragmatic.model.Account;
import main.java.com.pragmatic.model.Transaction;
import main.java.com.pragmatic.repository.AccountRepository;
import main.java.com.pragmatic.repository.TransactionRepository;

import java.io.IOException;

public class AccountService {
    public AccountService() {
    }
//    TODO add currency converter
//   TODO не певен що сюди ок докидати весь об'єкт репозиторія, щоб просто оновити файл.
//    Це залежить від реалізації, і моя просто така, чи я знову роблю діч? Може це просто перенести в репозиторій, і не мучитись? Але все в одне місце теж не впихнеш.

    public static Transaction moneyTransfer(Account accountFrom, Account accountTo, AccountRepository accounts,
                                            TransactionRepository transactions,
                                            Double amount ) throws IOException {
        if (accountFrom.getBalance() >= amount ) {
            accountFrom.setBalance(accountFrom.getBalance() - amount);
            accountTo.setBalance(accountTo.getBalance() + amount);
            Transaction transferTransaction = transactions.createNewTransaction(accountFrom.getId(), accountTo.getId(), amount);
//          TODO тут також не подобається, що в мене оновлення файлу прописується відносно аккаунтів тут, а в самому методіd add new transaction
            accounts.updateFile();
            System.out.println("Success money transfer");
            return  transferTransaction;
        }

        else {
            throw new RuntimeException("Error. Acc" + accountFrom.getId() + "from user " +  accountFrom.getUserId() + " does not have enough balance to make transaction");
        }


    }
}
