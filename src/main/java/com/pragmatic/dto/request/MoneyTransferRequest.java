package com.pragmatic.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class MoneyTransferRequest {
    Integer sourceAccountId;
    Integer destinationAccountId;
    BigDecimal amount;
}
