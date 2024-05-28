package com.pragmatic.service;

import static org.junit.jupiter.api.Assertions.*;

import com.pragmatic.model.Account;
import com.pragmatic.model.Transaction;
import com.pragmatic.repository.AccountRepository;
import com.pragmatic.repository.TransactionRepository;
import com.pragmatic.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {


    @Mock
    private AccountRepository accountsRepoTest;

    @Mock
    private TransactionRepository transactionsRepoTest;


    @InjectMocks
    AccountService accountServiceTest;

    @Test
    public void moneyTransferTest () throws IOException {
        Account accountTestFrom = new Account();

        Account accountTestTo = new Account();

        accountTestFrom.setBalance(20.0);
        accountTestTo.setBalance(0.0);

        assertEquals(20.0, accountTestFrom.getBalance());
        AccountService accountServiceTest = new AccountService(accountsRepoTest, transactionsRepoTest);


        Transaction transactionTest = accountServiceTest.moneyTransfer(accountTestFrom, accountTestTo, 10.0);

        assertEquals(10.0, accountTestTo.getBalance());
        assertEquals(10.0, accountTestFrom.getBalance());


        //       Транзакцію не писав, бо потрібно буде робити реальний об'єкт а не мок, і її.
        //        РОзібрався, що не можна витягувати і писати щось до мока. Тільки уявні якісь методи, і що вони мають ьвіддавати.

    }




}
