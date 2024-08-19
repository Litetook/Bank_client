package com.pragmatic.controller;
import com.pragmatic.model.Account;
import com.pragmatic.repository.AccountRepository;
import com.pragmatic.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@EnableAutoConfiguration
public class Controller {

    final
    AccountService accountService;
    private final AccountRepository accountRepository;
//    сюди інжектити сервіси, не репозиторії
//    в сервысы мын валыдацыю, а вже в ньому дьоргати репо
    public Controller(AccountService accountService, AccountRepository accountRepository) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "Teest") String name) {
        return String.format("Hello %s!", name);
    }

    @RequestMapping("/")
    public String home() {
        return "Hello World " + accountService.getAccount(1);
    }

    @RequestMapping("/account")
    public  String hello(@RequestParam(value = "id") Integer id ) {
        var account = accountService.getAccount(id);
        if (account == null ) {
            return  "account not found";
        }
        return  account;

    }

//   Те що зовні - не в базу, мають бути різні об'єкти перед передачою. Наприклад дто, наприклад акк дто ( дата транас обж)
//    Не завжди треба віддаватти всю інфу як в базі
//    один на рівні моделі ( те що в базу)
//    дто це те що віддаю наверх
//
    @PostMapping(path= "addAccount", consumes = "application/json", produces = "application/json" )
    public ResponseEntity<Account> addAccount(@RequestBody Account account) {

//        тут віддати аккаунт дто
//        окремо реквест, окремо резпонз
//      request, response, і те що до бази.
//        акк сервіс буде віддавати модель. її треба буде перегнати в дто
//        створення - контроллер (валідація на рівні арі), сервіс (передаю сирі дані, валідація на те чи все ок з акком загалом, чи є, чи нема, пробувати кидати через ексепшени),
//        далі репо. Почитати за екс зендлери, щоб створити для аккаунта свій
//        гет - контроллер, сервіс, і репозиторій. Якщо по ід і помилки нема - то треба віддати помилку. Спробувати віддати ліст ( наприклад всіх користувачів)
//        mvc описано спрінговий
//        update можна спробувати


        Account newAccount = accountRepository.addAccount(account);
        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }


}
