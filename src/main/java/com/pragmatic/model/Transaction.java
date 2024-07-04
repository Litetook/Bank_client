package com.pragmatic.model;

import java.time.Instant;
import java.util.Date;

public class Transaction  {
    private Integer id;
    private Integer accountFromId;
    private Integer accountToId;
    private Double amount;
    private Date actionDate;


    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + id +
                ", accountFromId=" + accountFromId +
                ", accountToId=" + accountToId +
                ", amount=" + amount +
                ", actionDate=" + actionDate +
                '}';
    }

    public Integer getAccountFromId() {
        return accountFromId;
    }

    public Integer getAccountToId() {
        return accountToId;
    }

    public Transaction (Integer accountToId, Integer accountFromId, Double amount) {
//        manual initiation, so needed write to file
        this.id = ++this.id;
        this.accountFromId = accountFromId;
        this.accountToId =  accountToId;
        this.amount = amount;
        this.actionDate = Date.from(Instant.now());
    }

    public Transaction() {
//        empty for importing and setting using csv file
        //TODO чи норм такі речі, в пустий конструктор таке класти?
            this.actionDate = Date.from(Instant.now());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public Double getAmount () {
        return this.amount;
    }

    public Long getTime() {
        return this.actionDate.toInstant().getEpochSecond();
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public void setAccountFromId(Integer accountFromId) {
        this.accountFromId = accountFromId;
    }

    public void setAccountToId(Integer accountToId) {
        this.accountToId = accountToId;
    }

    public Date getActionDate() {
        return actionDate;
    }
}

