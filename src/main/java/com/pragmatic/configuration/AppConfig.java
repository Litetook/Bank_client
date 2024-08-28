package com.pragmatic.configuration;

import com.pragmatic.model.Currency;
import com.pragmatic.repository.AccountRepository;
import com.pragmatic.repository.CurrencyRepository;
import com.pragmatic.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;

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
    public UserRepository userRepository() {
        return new UserRepository();
    }

    @Bean
    public AccountRepository accountRepository(UserRepository userRepository) throws IOException {
        return new AccountRepository(userRepository);
    }

}
