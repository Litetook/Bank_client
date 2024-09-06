package com.pragmatic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.Date;

@Accessors(chain = true)
@EqualsAndHashCode
public class Transaction  {
    @Setter @Getter
    @EqualsAndHashCode.Exclude
    private Integer id;
    @Setter @Getter private Integer accountFromId;
    @Setter @Getter private Integer accountToId;
    @Setter @Getter private Double amount;
    @Setter @Getter private Date actionDate;


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



}

