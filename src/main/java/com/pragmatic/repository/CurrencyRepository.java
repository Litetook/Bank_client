package com.pragmatic.repository;

import com.pragmatic.dao.FileDataProvider;
import com.pragmatic.model.Currency;


import java.util.*;
import java.util.stream.Collectors;

public class CurrencyRepository {
    private Integer lastid = 0;
    private Map<Integer, Currency> currencies;

//    private FileDataProvider file= new FileDataProvider(tableImportName);
//private static String tableImportName = "currency.csv";



    public CurrencyRepository() {
        this.currencies = new HashMap<>();

        String[] currenciesArray =  {"EUR", "USD", "KRW", "IDR"}
        ;


        for (String i: currenciesArray) {
            this.currencies.put(this.lastid, createCurrency(i));
        }



//        List<Currency> currencyList = new FileDataProvider(tableImportName).initCurrencyData();
//        currencyList.forEach(currency -> {
//            if (!this.currencies.containsKey(currency.getId())) {
//                this.currencies.put(currency.getId(), currency);
//                if (this.lastid < currency.getId()) {
//                    this.lastid = currency.getId();
//                }
//
//            }
//            else {
//                throw new IllegalStateException(currency.getId() + "this currencyid exists in repo");
//            }
//
//        });
    }

    protected Currency createCurrency(String code) {
        Currency newCurrency = new Currency();
        newCurrency.setId(this.lastid);
        ++this.lastid;
        newCurrency.setSymbol(code);
        return newCurrency;
    }

    public List<Currency> getRepoList() {
        return this.currencies.values().stream().collect(Collectors.toList());
    }

    public Currency getCurrencyById(Integer id) {
        try {
            return this.currencies.get(id);
        }
        catch (Exception e) {
            System.out.println("There is no currency with id" + id + " Request failed" + e.toString());
        }
        return null;
    }
}
