package com.pragmatic.model;

import jakarta.persistence.*;
import lombok.*;

@Getter  @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="currencies")
public class Currency  {
    @Id
    @SequenceGenerator(name = "currency_sequence", sequenceName = "currency_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currency_sequence")
    @Column(name = "currencyid")
    Integer id;
    String symbol;
}
