package main.java.com.pragmatic.model;

public class Currency extends BaseModel {
    Integer currencyid;
    String symbol;


    public Currency(Integer currencyid, String symbol) {
        this.currencyid = currencyid;
        this.symbol = symbol;
    }

    public Currency() {

    }


    public Integer getId() {
        return  this.currencyid;
    }

    public String getSymbol() {
        return  this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setCurrencyid(Integer currencyid) {
        this.currencyid = currencyid;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currencyid=" + currencyid +
                ", symbol='" + symbol + '\'' +
                '}';
    }

}
