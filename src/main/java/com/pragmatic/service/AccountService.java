package com.pragmatic.service;

import com.pragmatic.controller.exception.MoneyTransferException;
import com.pragmatic.controller.exception.ObjAlreadyExistsException;
import com.pragmatic.controller.exception.ObjNotFoundException;
import com.pragmatic.dto.AccountDto;
import com.pragmatic.controller.dto.request.MoneyTransferRequest;
import com.pragmatic.model.Account;
import com.pragmatic.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    AccountDto createAccountFromDto(AccountDto accountDto) throws ObjAlreadyExistsException;
    AccountDto getAccountById(Long id) throws ObjNotFoundException;
    List<AccountDto> findAccountsByUserId(Long userId);
    Transaction moneyTransfer(MoneyTransferRequest moneyTransferRequest) throws ObjNotFoundException, MoneyTransferException;
    Optional<Account> findExistAccountByParams(Long currencyId, Long userId);
}
