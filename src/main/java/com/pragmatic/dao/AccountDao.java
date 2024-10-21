package com.pragmatic.dao;


import com.pragmatic.model.Account;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountDao {
    Account save(Account account);
    Optional<Account> findById(Long id);
    Optional<Account> findAccountByUserIdAndCurrencyId(Long userId, Long currencyId);
    boolean updateBalance(BigDecimal balance,Long accountId);
    List<Account> findAccountsByUserId(Long userId);
}
