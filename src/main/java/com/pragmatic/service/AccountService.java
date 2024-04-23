package main.java.com.pragmatic.service;

import main.java.com.pragmatic.model.Account;
import main.java.com.pragmatic.model.Transaction;
import main.java.com.pragmatic.model.User;

public class AccountService {
    public AccountService() {
    }
//    TODO add currency converter
    public static Transaction moneyTransfer(Account accountFrom, Account accountTo, Double amount ) {
        if (accountFrom.getBalance() >= amount ) {
            accountFrom.setBalance(accountFrom.getBalance() - amount);
            accountTo.setBalance(accountTo.getBalance() + amount);
            Transaction transferTransaction =new Transaction(accountFrom.getAccountid(), accountTo.getAccountid(), amount);

            return  transferTransaction;
        }

        else {
            throw new RuntimeException("Error. Acc" + accountFrom.getAccountid() + "from user " +  accountFrom.getUserId() + " does not have enough balance to make transaction");
        }


    }
}
