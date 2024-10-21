package com.pragmatic.model;

import lombok.*;

@Getter  @Setter @ToString
@Builder
public class Currency  {
    Long id;
    String symbol;
}
