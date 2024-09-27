package com.pragmatic;


import com.pragmatic.dao.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
@NoArgsConstructor
@EnableConfigurationProperties
public class Main {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException {

        ApplicationContext appContext = SpringApplication.run(Main.class, args); //

//        UserDao userDao = (UserDao)  appContext.getBean("userDao");
//        User user1 = new User()
//                .setId(1)
//                .setName("bla")
//                .setEmail("bla")
//                .setPassword("blabla");
//        userDao.save(user1);

//        EXAMPLE HOW TO CREATE BEANS
//        CurrencyDaoImpl curRepo = (CurrencyDaoImpl) appContext.getBean("currencyRepository");
//        CurrencyDaoImpl curRepo2 = (CurrencyDaoImpl) appContext.getBean("currencyRepository");
//        System.out.println("Verify singleton");
//        System.out.println(curRepo2 == curRepo);

//        Currency currency1 = (Currency) appContext.getBean(("currency"));
//        Currency currency2 = (Currency) appContext.getBean(("currency"));

//        System.out.println("currency model comparing proto test");
//        System.out.println(currency1==currency2);

//        poverny anotation repository na accRepo pisla testiv
//        AccountDaoImpl accountRepositoryImpl = (AccountDaoImpl) appContext.getBean(AccountDaoImpl.class);

        //INIT
//        //Викликаю юзер репозиторій, який парсить файл одразу і створює в ньому об'єкти юзера по cs
//        UserDaoImpl userRepo = new UserDaoImpl();
//        System.out.println(userRepo.getRepoList());
//        CurrencyDaoImpl currencyRepo = new CurrencyDaoImpl();
//        userRepo.getUserById(1);
//        AccountDao accountRepo = new AccountDao(userRepo);
//        TransactionDaoImpl transRepo = new TransactionDaoImpl();
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

