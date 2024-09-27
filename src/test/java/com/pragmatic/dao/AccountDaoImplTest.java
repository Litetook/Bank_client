package com.pragmatic.dao;

import com.pragmatic.model.User;
import com.pragmatic.dao.impl.AccountDaoImpl;
import com.pragmatic.dao.impl.UserDaoImpl;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@Log4j2
//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class AccountDaoImplTest {
    @Mock
    UserDaoImpl userRepositoryImpl;  // Імітація UserDaoImpl

    @InjectMocks
    AccountDaoImpl accountDaoImpl; // Автоматичне вприскування залежностей

//    @BeforeEach
//    void setUp() {
//        // Налаштування поведінки mock для userRepositoryImpl
//        Mockito.when(userRepositoryImpl.getUserById(any(Integer.class))).thenReturn(new User());
//        log.debug("Test setup is complete.");
//        System.out.println("Test setup is complete.");
//
//    }

//    @Test
//    void createAccountTest() {
//        log.debug("Testing createAccount method...");
//        log.debug("User from repository: " + userRepositoryImpl.getUserById(1));
//        Assertions.assertNotNull(accountDaoImpl, "AccountDao should not be null");
//        Assertions.assertNotNull(accountDaoImpl.createAccount(1, 1), "Created account should not be null");
//    }

//    @Test
//    void getUserById () {
//        Account account = accountDaoImpl.createAccount(1,1);
//        Assertions.assertNotNull(accountDaoImpl.getAccountById(1));
//        Assertions.assertEquals(account, accountDaoImpl.getAccountById(account.getId()).get());
//    }

//    @Test
//    void getRepoList() {
//        Account acc1 = accountDaoImpl.createAccount(1,1);
//        Account acc2 = accountDaoImpl.createAccount(2,1);
//        List<Account> accountList = Arrays.asList(acc1,acc2);
//        Assertions.assertEquals(accountList, accountDaoImpl.getRepoList());
//    }



}