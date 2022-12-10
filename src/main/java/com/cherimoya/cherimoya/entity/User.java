package com.cherimoya.cherimoya.entity;

import com.cherimoya.cherimoya.enums.DocumentStatus;
import com.cherimoya.cherimoya.enums.UsersStatus;
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
@Entity(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NICKNAME")
    private String name;

    @Column(name = "USER_EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "CREATED_DATE")
    private Date date;

    @JoinColumn(name = "BALANCE")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Balance balance;

    @Column(name = "PROFILE_PHOTO")
    private String profilePhoto;

    @Column(name = "RECIPENT")
    private boolean isRecipent;

    @Column(name = "DOCUMENTSTATUS")
    private DocumentStatus documentStatus;

    @Column(name = "ROLES")
    private String roles;

    @Column(name = "USERS_STATUS")
    @Enumerated(EnumType.STRING)
    private UsersStatus usersStatus;

    @Column
    private Boolean active;

}