package com.pragmatic.service.impl;

import com.pragmatic.controller.exception.MoneyTransferException;
import com.pragmatic.controller.exception.ObjAlreadyExistsException;
import com.pragmatic.controller.exception.ObjNotFoundException;
import com.pragmatic.converter.DtoConverter;
import com.pragmatic.dao.impl.AccountDaoImpl;
import com.pragmatic.dto.impl.AccountDtoImpl;
import com.pragmatic.dao.impl.TransactionDaoImpl;
import com.pragmatic.dto.request.MoneyTransferRequest;
import com.pragmatic.model.Account;
import com.pragmatic.model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pragmatic.service.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Log4j2
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDaoImpl accountDao;

    @Autowired
    TransactionDaoImpl transactionDao;

    @Autowired
    DtoConverter dtoConverter;

    public AccountServiceImpl(AccountDaoImpl accountDao,
                              TransactionDaoImpl transactionDao) {
        this.transactionDao = transactionDao;
        this.accountDao = accountDao;
    }


    @Override
    public AccountDtoImpl getAccountById(Integer id) throws ObjNotFoundException {
        Optional<Account> optionalAccount =  accountDao.findById(id);
        if (optionalAccount.isEmpty()) {
            throw new ObjNotFoundException(String.format("There is no account with id %d", id));
        }
        return dtoConverter.convertAccountToDto(optionalAccount.get());
    }


    public AccountDtoImpl createAccountFromDto(AccountDtoImpl inputAccountDto) throws ObjAlreadyExistsException {
        Optional<Account> existingAccount = accountDao.findExistAccountByParams(inputAccountDto);
        if (existingAccount.isEmpty()) {
            Account inputAccount = dtoConverter.convertDtoToAccount(inputAccountDto);
            this.accountDao.save(inputAccount);
            return dtoConverter.convertAccountToDto(inputAccount);
        }
        throw new ObjAlreadyExistsException(String.format("Account already exists with id %d", existingAccount.get().getId()));
    }

    public Transaction moneyTransfer(MoneyTransferRequest moneyTransferRequest) throws ObjNotFoundException, MoneyTransferException {
//        VERIFY IF EXISTS
//        VERIFY BALANCE
//        DO TRANSFER
//        UPDATE BALANCES
//        RETURN TRANSACTION
        //        VERIFY BALANCE
        Optional<Account> optionalSourceAccount = accountDao.findById(moneyTransferRequest.getSourceAccountId());
        Optional<Account> optionalDestinationAccount = accountDao.findById(moneyTransferRequest.getDestinationAccountId());
        Double amount = moneyTransferRequest.getAmount();

        if  ( optionalSourceAccount.isEmpty() ){
            throw new ObjNotFoundException("source account does not exist");
        } else if (optionalDestinationAccount.isEmpty()) {
            throw new ObjNotFoundException("destination account does not exist");
        }


        Account destinationAccount = optionalDestinationAccount.get();
        Account sourceAccount = optionalSourceAccount.get();

        if (sourceAccount.getBalance() < amount) { //TODO THINK WHY IT IS NOT CORRECT
            throw new MoneyTransferException("source acc balance is not enough to make transaction");
        }


        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        destinationAccount.setBalance(destinationAccount.getBalance() + amount);

        accountDao.updateBalance(sourceAccount.getBalance(), sourceAccount.getId());
        //TODO WHAT IF ROWNUMBER 92 FALL WITH EXCEPTION. WHAT WILL BE SAVED TO DATABASE?
        accountDao.updateBalance(destinationAccount.getBalance(), destinationAccount.getId());

        return this.transactionDao.save(Transaction.builder()
                .destinationAccountId(destinationAccount.getId())
                .sourceAccountId(sourceAccount.getId())
                .amount(amount)
                .build()
        );
    }


    @Override
    public List<AccountDtoImpl> findAccountsByUserId(Integer userId) {
        List<AccountDtoImpl> accountDtoList = new ArrayList<>();
        accountDao.findAccountsByUserId(userId).forEach(account -> {
            accountDtoList.add(dtoConverter.convertAccountToDto(account));
        });
        return accountDtoList;
    }
    @Override
    public Optional<Account> findExistAccountByParams(AccountDtoImpl accountDtoImpl) {
        return this.accountDao.findExistAccountByParams(accountDtoImpl);
    }

}