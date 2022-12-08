package com.cherimoya.cherimoya.entity;

import com.cherimoya.cherimoya.enums.BalanceStatus;
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

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "requisites")
    private String requisites;

    @Column(name = "cash")
    private BigDecimal count;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private BalanceStatus status;
}
