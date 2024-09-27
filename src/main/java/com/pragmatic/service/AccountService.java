package com.pragmatic.service;

import com.pragmatic.controller.exception.MoneyTransferException;
import com.pragmatic.controller.exception.ObjAlreadyExistsException;
import com.pragmatic.controller.exception.ObjNotFoundException;
import com.pragmatic.dto.impl.AccountDtoImpl;
import com.pragmatic.dto.request.MoneyTransferRequest;
import com.pragmatic.model.Account;
import com.pragmatic.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    AccountDtoImpl createAccountFromDto(AccountDtoImpl accountDto) throws ObjAlreadyExistsException;
    AccountDtoImpl getAccountById(Integer id) throws ObjNotFoundException;
    List<AccountDtoImpl> findAccountsByUserId(Integer userId);
    Transaction moneyTransfer(MoneyTransferRequest moneyTransferRequest) throws ObjNotFoundException, MoneyTransferException;
    Optional<Account> findExistAccountByParams(AccountDtoImpl accountDtoImpl);
}
