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
        accountTestFrom.setId(1);
        accountTestTo.setBalance(0.0);
        accountTestTo.setId(2);

        Transaction transaction = new Transaction();
        transaction.setId(1);
        transaction.setAccountFromId(1);
        transaction.setAccountToId(2);
        transaction.setAmount(10.0);


        Mockito.when(transactionsRepoTest.createNewTransaction(1,2,10.0)).thenReturn(transaction);
//        assertEquals(20.0, accountTestFrom.getBalance());


        Transaction transactionTest = accountServiceTest.moneyTransfer(accountTestFrom, accountTestTo, 10.0);

        assertEquals(10.0, accountTestTo.getBalance());
        assertEquals(10.0, accountTestFrom.getBalance());

        assertEquals(10.0, transactionTest.getAmount());
        assertEquals(1, transactionTest.getAccountFromId());
        assertEquals(2, transactionTest.getAccountToId());

        //       Транзакцію не писав, бо потрібно буде робити реальний об'єкт а не мок, і її.
        //        РОзібрався, що не можна витягувати і писати щось до мока. Тільки уявні якісь методи, і що вони мають ьвіддавати.

    }




}
