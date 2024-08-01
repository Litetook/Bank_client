package com.pragmatic;


import com.pragmatic.repository.AccountRepository;
import com.pragmatic.repository.CurrencyRepository;
import com.pragmatic.repository.TransactionRepository;
import com.pragmatic.repository.UserRepository;
import com.pragmatic.service.AccountService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class Main {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException {
        SpringApplication.run(Main.class, args);

//        //INIT
//        //Викликаю юзер репозиторій, який парсить файл одразу і створює в ньому об'єкти юзера по cs
//        UserRepository userRepo = new UserRepository();
//        System.out.println(userRepo.getRepoList());
//        CurrencyRepository currencyRepo = new CurrencyRepository();
//        userRepo.getUserById(1);
//        AccountRepository accountRepo = new AccountRepository(userRepo);
//        TransactionRepository transRepo = new TransactionRepository();
//
//        userRepo.createUser("manual", "blabla", "pass");
//        AccountService accService = new AccountService(accountRepo, transRepo);
//
//        accService.moneyTransfer(accountRepo.getAccountById(2), accountRepo.getAccountById(3) ,2.0);
//
////        transRepo.getAccountTransactionsByDateRange(3, 1714928448, ;
//        System.out.println("Trying to get transactions by range of dates and acc id");
//        System.out.println(transRepo.getAccountTransactionsByDateRange(1, 1710945474, 1715018242 ));
    }


}

