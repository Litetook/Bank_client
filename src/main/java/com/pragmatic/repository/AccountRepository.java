package com.pragmatic.repository;

import com.pragmatic.dto.AccountDto;
import com.pragmatic.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
   Optional<Account> getAccountById(Integer id);
//   Account createAccount(AccountDto accountDto);
}
