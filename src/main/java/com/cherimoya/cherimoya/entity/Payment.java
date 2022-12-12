package com.cherimoya.cherimoya.entity;

import com.cherimoya.cherimoya.enums.PaymentStatus;
import com.cherimoya.cherimoya.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_date")
    private Date date;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "status")
    private PaymentStatus status;

    @JoinColumn(name = "FromBalance")
    @OneToOne(fetch = FetchType.EAGER)
    private Balance fromBalance;

    @JoinColumn(name = "ToBalance")
    @OneToOne(fetch = FetchType.EAGER)
    private Balance toBalance;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private PaymentType type;

}
