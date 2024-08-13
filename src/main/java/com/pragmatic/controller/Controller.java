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

    @Autowired
    AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;

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

    @PostMapping(path= "addAccount", consumes = "application/json", produces = "application/json" )
    public ResponseEntity<Account> addAccount(@RequestBody Account account) {
        Account newAccount = accountRepository.addAccount(account);
        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }


}
