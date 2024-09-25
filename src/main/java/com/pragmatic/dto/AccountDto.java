package com.pragmatic.dto;


import com.pragmatic.model.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class AccountDto {
//    Як конвертер для моделі, ті ж самі аккаунти, але дані віддаю я інші, обмежені по ним.
    private Integer accountId;
    private Integer currencyId;
    private Integer userId;
    private Double balance;

    public AccountDto(Account account) {
        this.accountId = account.getId();
        this.userId = account.getUserId();
        this.currencyId = account.getCurrencyId();
        this.balance = account.getBalance();
    }



}



