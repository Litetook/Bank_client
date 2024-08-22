package com.pragmatic.repository;

import com.pragmatic.model.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountRepository {
   List<Account> getRepoList ();
   Account createAccount(Integer currencyId, Integer userId);
   Optional<Account> getAccountById(Integer id);
}
