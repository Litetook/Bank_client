package com.pragmatic.dao;

import com.pragmatic.model.Currency;

import java.util.Optional;

public interface CurrencyDao {
    Optional<Currency> findCurrencyById(Long id);
    Optional<Currency> findCurrencyBySymbol(String symbol);

}
