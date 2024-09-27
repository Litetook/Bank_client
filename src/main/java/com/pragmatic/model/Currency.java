package com.pragmatic.model;

import jakarta.persistence.*;
import lombok.*;

@Getter  @Setter @ToString
@Builder
public class Currency  {
    Integer id;
    String symbol;
}
