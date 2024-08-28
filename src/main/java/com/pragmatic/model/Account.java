package com.pragmatic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class Account {
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


}
