package com.pragmatic.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@EqualsAndHashCode
@ToString @Getter @Setter
@Builder
public class Transaction  {
    private Long transactionId;
    private final Long sourceAccountId;
    private final Long destinationAccountId;
    private final BigDecimal amount;
    @Builder.Default
    private Date actionDate = Date.from(Instant.now());
}

