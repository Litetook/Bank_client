package com.pragmatic;


import com.pragmatic.controller.Controller;
import com.pragmatic.model.Currency;
import com.pragmatic.repository.*;
import com.pragmatic.service.AccountService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import lombok.extern.flogger.Flogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class Main {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException {

        ApplicationContext appContext = SpringApplication.run(Main.class, args); //

        CurrencyRepository curRepo = (CurrencyRepository) appContext.getBean("currencyRepository");
        CurrencyRepository curRepo2 = (CurrencyRepository) appContext.getBean("currencyRepository");
        System.out.println("Verify singleton");
        System.out.println(curRepo2 == curRepo);

        Currency currency1 = (Currency) appContext.getBean(("currency"));
        Currency currency2 = (Currency) appContext.getBean(("currency"));

        System.out.println("currency model comparing proto test");
        System.out.println(currency1==currency2);

//        poverny anotation repository na accRepo pisla testiv
        AccountRepository accountRepository = (AccountRepository) appContext.getBean(AccountRepository.class);
        System.out.println(accountRepository.getRepoList());


        //INIT
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

