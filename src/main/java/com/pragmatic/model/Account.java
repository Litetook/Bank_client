package com.pragmatic.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Objects;

@Getter @Setter @ToString
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class Account {
    long id;
    long userId;
    long currencyId;
    BigDecimal balance ;


    @Override
    public boolean equals(Object o) {
        if (this == o ) return  true;
        if (o == null  || getClass() != o.getClass()) return  false;
        Account acc = (Account)  o;
        return  id == acc.id && userId == acc.userId && currencyId == acc.currencyId ;
    }



    @Override
    public int hashCode() {
//        int result = 17;
//        result = 31* result * id;
//        result = 31* result * currencyId;
//        result = 31 * result * userId;
//        return  result
//        Замінив верхній код на
        return Objects.hash(17, id, currencyId, userId);
    }

}
