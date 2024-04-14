package model;

public class Account extends BaseModel {

    Integer id;

    Currency currency;

    Double balance ;


    public Account (Currency currency, Integer id) {
        this.currency = currency;
        this.id = id;
        this.balance = 0.0;
    }

    public  Integer getId() { return  this.id; }

    public  Currency getCurrency () { return  this.currency; }

    public Double getBalance() { return  this.balance; }

    public void setBalance(double balance) {
        this.balance = balance;

    }


}
