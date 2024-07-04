package com.pragmatic.model;


public class Account implements IModel {
    Integer id;
    Integer userId;
    Integer currencyId;
    Double balance ;


    public Account (Integer currencyId, Integer id, Integer userId) {
        this.id = id;
        this.currencyId = currencyId;
        this.balance = 0.0;
        this.userId = userId;
    }

    public Account() {
    }

    public Double getBalance() { return  this.balance; }

    public Integer getUserId() {
        return userId;
    }

    public void setId(Integer accountid) {
        this.id = accountid;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setUserid(Integer userid) {
        this.userId = userid;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getCurrencyId() {
        return this.currencyId;
    }


    @Override
    public String toString() {
        return "Account{" +
                "accountid=" + id +
                ", userId=" + userId +
                ", currencyId=" + currencyId +
                ", balance=" + balance +
                '}';
    }
}
