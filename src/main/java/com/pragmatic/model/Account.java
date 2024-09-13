package com.pragmatic.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Objects;

@Entity
@Table(name="accounts")
@Getter @Setter @NoArgsConstructor @ToString
@Accessors(chain = true)
public class Account {
    @Id
    @SequenceGenerator(name = "account_seq", sequenceName = "account_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    int id;
    int userId;
    int currencyId;
    Double balance ;

    public Account (Integer currencyId,  Integer userId) {
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
