package com.pragmatic.model;

import com.pragmatic.dto.AccountDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Objects;

@Getter @Setter @ToString
@Accessors(chain = true)
@Builder
public class Account {
    int id;
    int userId;
    int currencyId;
    Double balance ;




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
