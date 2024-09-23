package com.pragmatic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.Date;

@Accessors(chain = true)
@EqualsAndHashCode
@ToString
public class Transaction  {
    @Setter @Getter
    @EqualsAndHashCode.Exclude
    @Column(name="transactionid")

    private Integer id;
    @Setter @Getter private Integer accountFromId;
    @Setter @Getter private Integer accountToId;
    @Setter @Getter private Double amount;
    @Setter @Getter private Date actionDate;


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
            this.actionDate = Date.from(Instant.now());
    }



}

