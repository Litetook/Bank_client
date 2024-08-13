package com.pragmatic.repository;

import com.pragmatic.model.Currency;

import java.util.List;

public interface ICurrencyRepository {
    Currency createCurrency(String code);
    List<Currency> getRepoList();
    Currency getCurrencyById(Integer id);

}
