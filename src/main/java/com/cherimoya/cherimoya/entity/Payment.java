package com.cherimoya.cherimoya.entity;

import com.cherimoya.cherimoya.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private int amount;

    @Column(name = "status")
    private PaymentStatus status;

    @JoinColumn(name = "FromUser")
    @OneToOne(fetch = FetchType.LAZY)
    private Balance fromBalance;

    @JoinColumn(name = "ToBalance")
    @OneToOne(fetch = FetchType.LAZY)
    private Balance toBalance;

    @JoinColumn(name = "post")
    @OneToOne(fetch = FetchType.LAZY)
    private Post post;

}
