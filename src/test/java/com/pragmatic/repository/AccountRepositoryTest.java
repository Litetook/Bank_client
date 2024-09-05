package com.pragmatic.repository;

import com.pragmatic.model.Account;
import com.pragmatic.model.User;
import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@Log4j2
@RunWith(MockitoJUnitRunner.class)
//@ExtendWith(MockitoExtension.class)
class AccountRepositoryTest {
    @Mock
    UserRepository userRepository;  // Імітація UserRepository

    @InjectMocks
    AccountRepository accountRepository; // Автоматичне вприскування залежностей

    @Before
    void setUp() {
        // Налаштування поведінки mock для userRepository
        log.debug("BeforeEach setup started.");
        log.debug("UserRepository: " + userRepository);
        log.debug("AccountRepository: " + accountRepository);
        User mockUser = new User();
        Mockito.when(userRepository.getUserById(any(Integer.class))).thenReturn(mockUser);
        log.debug("AccountRepository is: " + accountRepository);
        log.debug("Test setup is complete.");
    }

    @Test
    void createAccountTest() {
        log.debug("Testing createAccount method...");
        log.debug("User from repository: " + userRepository.getUserById(1));

        Assertions.assertNotNull(accountRepository, "AccountRepository should not be null");
        Assertions.assertNotNull(accountRepository.createAccount(1, 1), "Created account should not be null");
    }

    @Test
    void getUserById () {
        Account account = accountRepository.createAccount(1,1);
        Assertions.assertNotNull(accountRepository.getAccountById(1));
        Assertions.assertEquals(account, accountRepository.getAccountById(account.getId()).get());
    }


    @Test
    void getRepoList() {} {
        Account acc1 = accountRepository.createAccount(1,1);
        Account acc2 = accountRepository.createAccount(2,1);
        List<Account> accountList = Arrays.asList(acc1,acc2);
        Assertions.assertEquals(accountList, accountRepository.getRepoList());
    }

    @Test //simple test just
    void simpleTest() {
        int x = 2;
        int y = 23;
        Assertions.assertEquals(25, x+y);
    }



}