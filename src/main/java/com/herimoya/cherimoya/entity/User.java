package com.herimoya.cherimoya.entity;

import com.herimoya.cherimoya.enums.DocumentStatus;
import com.herimoya.cherimoya.enums.RoleStatus;
import com.herimoya.cherimoya.enums.UsersStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.EnableMBeanExport;

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

    @Column(name = "REQUISITE")
    private String requisite;

    @Column(name = "PROFILE_PHOTO")
    private String profilePhoto;

    @Column(name = "RECIPENT")
    private boolean isRecipent;

    @Column(name = "DOCUMENTSTATUS")
    private DocumentStatus documentStatus;

    @Column(name = "ROLES")
    @Enumerated(EnumType.STRING)
    private RoleStatus roles;

    @Column(name = "USERS_STATUS")
    @Enumerated(EnumType.STRING)
    private UsersStatus usersStatus;

    @Column
    private Boolean active;
}