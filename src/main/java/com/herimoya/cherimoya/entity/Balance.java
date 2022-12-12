package com.herimoya.cherimoya.entity;


import com.herimoya.cherimoya.enums.BalanceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "BALANCE")
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "requisites")
    private String requisites;

    @Column(name = "balance")
    private BigDecimal amount;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private BalanceStatus status;
}