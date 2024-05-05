package main.java.com.pragmatic.repository;

import main.java.com.pragmatic.dao.FileDataProvider;
import main.java.com.pragmatic.model.Currency;
import main.java.com.pragmatic.model.User;


import java.util.*;

public class CurrencyRepository {
    private static String tableImportName = "currency.csv";
    private Integer lastid = 0;
    private Map<Integer, Currency> currencies;


    public CurrencyRepository() {
        this.currencies = new HashMap<>();
        List<Currency> currencyList = new FileDataProvider(tableImportName).initCurrencyData();
        currencyList.forEach(currency -> {
            if (!this.currencies.containsKey(currency.getId())) {
                this.currencies.put(currency.getId(), currency);
                if (this.lastid < currency.getId()) {
                    this.lastid = currency.getId();
                }

            }
            else {
                throw new IllegalStateException(currency.getId() + "this currencyid exists in repo");
            }

        });
    }

    public List<Currency> getRepoList() {
        return this.currencies.values().stream().toList();
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
