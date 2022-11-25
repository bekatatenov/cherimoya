package com.cherimoya.cherimoya.entity;

import com.cherimoya.cherimoya.enums.BalanceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "BALANCE")
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    private long user_id;

    @Column(name = "cash")
    private long count;


    @Column(name="status")
    private BalanceStatus status;
}
