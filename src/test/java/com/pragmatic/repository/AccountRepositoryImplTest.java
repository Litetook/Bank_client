package com.pragmatic.repository;

import com.pragmatic.model.Account;
import com.pragmatic.model.User;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@Log4j2
//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class AccountRepositoryImplTest {
    @Mock
    UserRepository userRepository;  // Імітація UserRepository

    @InjectMocks
    AccountRepositoryImpl accountRepositoryImpl; // Автоматичне вприскування залежностей

    @BeforeEach
    void setUp() {
        // Налаштування поведінки mock для userRepository
        Mockito.when(userRepository.getUserById(any(Integer.class))).thenReturn(new User());
        log.debug("Test setup is complete.");
        System.out.println("Test setup is complete.");

    }

//    @Test
//    void createAccountTest() {
//        log.debug("Testing createAccount method...");
//        log.debug("User from repository: " + userRepository.getUserById(1));
//        Assertions.assertNotNull(accountRepositoryImpl, "AccountRepository should not be null");
//        Assertions.assertNotNull(accountRepositoryImpl.createAccount(1, 1), "Created account should not be null");
//    }

//    @Test
//    void getUserById () {
//        Account account = accountRepositoryImpl.createAccount(1,1);
//        Assertions.assertNotNull(accountRepositoryImpl.getAccountById(1));
//        Assertions.assertEquals(account, accountRepositoryImpl.getAccountById(account.getId()).get());
//    }

//    @Test
//    void getRepoList() {
//        Account acc1 = accountRepositoryImpl.createAccount(1,1);
//        Account acc2 = accountRepositoryImpl.createAccount(2,1);
//        List<Account> accountList = Arrays.asList(acc1,acc2);
//        Assertions.assertEquals(accountList, accountRepositoryImpl.getRepoList());
//    }



}