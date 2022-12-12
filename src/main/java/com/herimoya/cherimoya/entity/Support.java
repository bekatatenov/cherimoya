package com.herimoya.cherimoya.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Supports")
public class Support {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "Mobile_Phone")
    private String phone;

    @Column(name = "Message")
    private String message;

    @Column(name = "Date")
    private String date;

    public Support(String name, String email, String number, String message) {
    }
}