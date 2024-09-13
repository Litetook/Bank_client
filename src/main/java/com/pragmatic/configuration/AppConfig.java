package com.pragmatic.configuration;

import com.pragmatic.model.Account;
import com.pragmatic.model.Currency;
import com.pragmatic.repository.CurrencyRepository;
import com.pragmatic.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    public CurrencyRepository currencyRepository() {
        return new CurrencyRepository();
    }

    @Bean
    @Scope("prototype")
    public Currency currency() {
        return new Currency();
    }

    @Bean
    @Scope("prototype")
    public Account account() {return new Account(); }


    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }



}
