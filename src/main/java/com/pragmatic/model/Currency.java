package com.pragmatic.model;

public class Currency  {
    Integer id;
    String symbol;


    public Currency(Integer id, String symbol) {
        this.id = id;
        this.symbol = symbol;
    }

    public Currency() {

    }


    public Integer getId() {
        return  this.id;
    }

    public String getSymbol() {
        return  this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currencyid=" + id +
                ", symbol='" + symbol + '\'' +
                '}';
    }

}
