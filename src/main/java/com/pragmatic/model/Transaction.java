package com.pragmatic.model;

import lombok.*;

import java.time.Instant;
import java.util.Date;

@EqualsAndHashCode
@ToString @Getter @Setter
@Builder
public class Transaction  {
    private Integer transactionId;
    private final Integer sourceAccountId;
    private final Integer destinationAccountId;
    private final Double amount;
    @Builder.Default
    private Date actionDate = Date.from(Instant.now());
}

