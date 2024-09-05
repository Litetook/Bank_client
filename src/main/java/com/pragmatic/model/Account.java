package com.pragmatic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Objects;

@Getter @Setter @NoArgsConstructor @ToString
@Accessors(chain = true)
public class Account {
    int id;
    int userId;
    int currencyId;
    Double balance ;

    public Account (Integer currencyId, Integer id, Integer userId) {
        this.id = id;
        this.currencyId = currencyId;
        this.balance = 0.0;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o ) return  true;
        if (o == null  || getClass() != o.getClass()) return  false;
        Account acc = (Account)  o;
        return  id == acc.id && userId == acc.userId && currencyId == acc.currencyId && balance.equals(acc.balance);
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
