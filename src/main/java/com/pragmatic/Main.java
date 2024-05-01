package main.java.com.pragmatic;//import main.java.com.pragmatic.model.User;


import main.java.com.pragmatic.model.Account;
import main.java.com.pragmatic.repository.AccountRepository;
import main.java.com.pragmatic.repository.CurrencyRepository;
import main.java.com.pragmatic.repository.TransactionRepository;
import main.java.com.pragmatic.repository.UserRepository;
import main.java.com.pragmatic.service.AccountService;

import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        //      Викликаю юзер репозиторій, який парсить файл одразу і створює в ньому об'єкти юзера по cs
        UserRepository userRepo = new UserRepository();
        System.out.println(userRepo.getRepoList());
        CurrencyRepository currencyRepo = new CurrencyRepository();
        AccountRepository accountRepo = new AccountRepository(userRepo);

//        USER TEST
        System.out.println(userRepo.getRepoList());
        System.out.println(userRepo.getUserById(1));

        userRepo.createUser("manual", "blabla", "pass");
        System.out.println(accountRepo.getRepoList());
        accountRepo.getAccountById(1).setBalance(2.0);
        AccountService.moneyTransfer(accountRepo.getAccountById(1), accountRepo.getAccountById(2), 2.0);
        TransactionRepository transRepo = new TransactionRepository();
        System.out.println(transRepo.getRepoList());

//
//        System.out.println("Acc from");
//        System.out.println(accountRepo.getAccountById(1));
//        System.out.println("Acc to");
//        System.out.println(accountRepo.getAccountById(2));
//        System.out.println(transRepo.getRepoList());
//
//        System.out.println("Trying to get transactions by range of dates and acc id");
//        System.out.println(transRepo.getAccountTransactionsByDateRange(1, 1710945474, 1714229665 ));



    }
}

