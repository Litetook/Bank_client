package main.java.com.pragmatic.model;


public class Account {
    Integer accountid;


    Integer userid;
    Currency currency;
    Double balance ;


    public Account (Currency currency, Integer id, User user) {
        this.accountid = id;
        this.currency = currency;
        this.balance = 0.0;
        this.userid = user.getId();
    }

    public Account() {
    }

    public Double getBalance() { return  this.balance; }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

}
