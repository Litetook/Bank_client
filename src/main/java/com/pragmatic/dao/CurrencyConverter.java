package main.java.com.pragmatic.dao;

import main.java.com.pragmatic.model.Currency;

public class CurrencyConverter {
    public static Currency parseCurrency(String data) {
        String[] values = data.split(",");

        if (values.length != 2) {
            throw new IllegalArgumentException("Invalid user data");
        }

        Currency currency = new Currency();
        currency.setCurrencyid(Integer.valueOf(values[0]));
        currency.setSymbol(values[1]);
        return currency;
    }
}
