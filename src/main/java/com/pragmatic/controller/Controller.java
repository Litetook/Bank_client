package com.pragmatic.controller;
import com.pragmatic.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableAutoConfiguration
public class Controller {
    @Autowired
    AccountService accountService;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "Teest") String name) {
        return String.format("Hello %s!", name);
    }

    @RequestMapping("/")
    public String home() {

        return "Hello World " + accountService.getAccount(1);
    }


}
