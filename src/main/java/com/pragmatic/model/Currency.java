package com.pragmatic.model;

public class Currency extends BaseModel {
    String symbol;

    public Currency(String symbol) {
        this.symbol = symbol;

    }

    public String getSymbol() {
        return  this.symbol;
    }
}
