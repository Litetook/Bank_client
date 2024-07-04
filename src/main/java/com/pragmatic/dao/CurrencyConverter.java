package com.pragmatic.dao;

import com.pragmatic.model.Currency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CurrencyConverter {

    private static List<String> csvSchema = new ArrayList<>(Arrays.asList("id", "symbol"));
    public static Currency parseCurrency(String data) {
        String[] values = data.split(",");

        if (values.length != 2) {
            throw new IllegalArgumentException("Invalid user data");
        }

        Currency currency = new Currency();
        currency.setId(Integer.valueOf(values[0]));
        currency.setSymbol(values[1]);
        return currency;
    }

    public static String currencyFileDataCreator(List<Currency> currencyList) {
        StringBuilder string = new StringBuilder();
//       HEADER
        string.append(String.join(",", csvSchema));
        if (currencyList.size() > 0) {
            string.append("\n");
        }
        for (int i = 0; i <= currencyList.size() -1; i++) {
            Currency currency = currencyList.get(i);
            string.append(String.join(",", currency.getId().toString().toString(), currency.getSymbol()));

        }
        return  string.toString();
    }
}
