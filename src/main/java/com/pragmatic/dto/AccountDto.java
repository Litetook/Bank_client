package com.pragmatic.dto;


import com.pragmatic.model.Account;
import com.pragmatic.repository.AccountRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class AccountDto {
//    Як конвертер для моделі, ті ж самі аккаунти, але дані віддаю я інші, обмежені по ним.
    private Integer id;
    private Integer currencyId;
    private Integer userId;
    private Double balance;

    public AccountDto() {}

    public AccountDto(Integer id, Integer userId, Integer currencyId, Double balance ) {
        this.id = id;
        this.userId = userId;
        this.currencyId = currencyId;
        this.balance = balance;
    }

    public AccountDto(Account account) {
        this.id = account.getId();
        this.userId = account.getUserId();
        this.currencyId = account.getCurrencyId();
        this.balance = account.getBalance();;
    }



}



