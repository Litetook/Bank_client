package com.pragmatic.dto.request;

import lombok.*;

import java.util.Date;

@Setter @Getter @Builder
@AllArgsConstructor
public class TransactionsByRangeRequest {
    private Date dateFrom;
    private Date dateTo;
    private Integer accountId;
}
