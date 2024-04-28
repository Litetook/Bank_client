package main.java.com.pragmatic.model;


public class Account extends BaseModel implements IModel {
    Integer accountid;
    Integer userId;
    Integer currencyId;
    Double balance ;




    public Account (Currency currency, Integer id, User user) {
        this.accountid = id;
        this.currencyId = currency.getId();
        this.balance = 0.0;
        this.userId = user.getId();
    }

    public Account() {
    }

    public Double getBalance() { return  this.balance; }

    public Integer getUserId() {
        return userId;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
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

    public Integer getAccountid() {
        return this.accountid;
    }


    @Override
    public String toString() {
        return "Account{" +
                "accountid=" + accountid +
                ", userId=" + userId +
                ", currencyId=" + currencyId +
                ", balance=" + balance +
                ", lineId=" + lineId +
                '}';
    }
}
