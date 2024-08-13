package com.pragmatic.repository;

import com.pragmatic.model.Account;

import java.util.List;

public interface IAccountRepository {
   List<Account> getRepoList ();
   Account createAccount(Integer currencyId, Integer userId);
   Account getAccountById(Integer id);
   Account addAccount(Account newAccount);
}
