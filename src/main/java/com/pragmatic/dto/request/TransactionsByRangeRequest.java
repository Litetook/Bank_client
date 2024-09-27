package com.pragmatic.dto.request;

import lombok.*;

import java.util.Date;

@Setter @Getter @Builder
@AllArgsConstructor
public class TransactionsByRangeRequest {
    private Long dateFrom;
    private Long dateTo;
    private Integer accountId;
}
