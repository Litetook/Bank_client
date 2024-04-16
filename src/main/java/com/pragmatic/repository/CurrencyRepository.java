package main.java.com.pragmatic.repository;

import main.java.com.pragmatic.dao.FileDataProvider;
import main.java.com.pragmatic.model.Currency;
import main.java.com.pragmatic.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyRepository {
    private static String tableImportName = "currency.csv";
    private Integer lastid = 0;
    private Map<Integer, Currency> currencies;

    public CurrencyRepository() {
        this.currencies = new HashMap<>();
        List<Currency> currencyList = new FileDataProvider(tableImportName).initCurrencyData();
        currencyList.forEach(currency -> {
            this.currencies.put(currency.getId(), currency);
            this.lastid++;
        });
    }

    public Map<Integer, Currency> getCurrencyList() {
        return this.currencies;
    }
}
