package com.pragmatic.controller.dto.request;

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
    Long sourceAccountId;
    Long destinationAccountId;
    BigDecimal amount;
}
