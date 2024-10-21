package com.pragmatic.dto;


import lombok.*;

import java.math.BigDecimal;

@Getter @Setter @ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AccountDto {
//    Як конвертер для моделі, ті ж самі аккаунти, але дані віддаю я інші, обмежені по ним.
    private Long accountId;
    private Long currencyId;
    private Long userId;
    private BigDecimal balance;
}





