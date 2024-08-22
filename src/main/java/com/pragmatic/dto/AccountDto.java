package com.pragmatic.dto;


import com.pragmatic.repository.AccountRepository;

public class AccountDto {
//    Як конвертер для моделі, ті ж самі аккаунти, але дані віддаю я інші, обмежені по ним.
    private Integer id;
    private Integer currencyId;
    private Integer userId;
    private Double balance;

    public AccountDto() {}

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public AccountDto(Integer id, Integer userId, Integer currencyId, Double balance ) {
        this.id = id;
        this.userId = userId;
        this.currencyId = currencyId;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public Double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "id=" + id +
                ", currencyId=" + currencyId +
                ", userId=" + userId +
                ", balance=" + balance +
                '}';
    }
}



