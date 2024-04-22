package main.java.com.pragmatic.model;

import java.time.LocalDate;

public class Transaction {
    private Integer transactionId = 0;
    private Integer accountFromId;
    private Integer accountToId;
    private Double amount;
    private LocalDate actionDate;


    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
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
        this.transactionId = ++this.transactionId;
        this.accountFromId = accountFromId;
        this.accountToId =  accountToId;
        this.amount = amount;
        this.actionDate = LocalDate.now();
    }

    public Transaction() {
//        empty for importing and setting using csv file
    }

    public Integer getId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }


    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setActionDate(LocalDate actionDate) {
        this.actionDate = actionDate;
    }

    public void setAccountFromId(Integer accountFromId) {
        this.accountFromId = accountFromId;
    }

    public void setAccountToId(Integer accountToId) {
        this.accountToId = accountToId;
    }

    public LocalDate getActionDate() {
        return actionDate;
    }
}

