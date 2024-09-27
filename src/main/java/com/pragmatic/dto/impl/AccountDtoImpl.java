package com.pragmatic.dto.impl;


import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AccountDtoImpl {
//    Як конвертер для моделі, ті ж самі аккаунти, але дані віддаю я інші, обмежені по ним.
    private Integer accountId;
    private Integer currencyId;
    private Integer userId;
    private Double balance;
}





