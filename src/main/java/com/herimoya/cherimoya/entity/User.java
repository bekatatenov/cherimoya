package com.herimoya.cherimoya.entity;

import com.Project.Post2.enums.DocumentStatus;
import com.Project.Post2.enums.RoleStatus;
import com.Project.Post2.enums.UsersStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @Column(name = "REQUISITE")
    private String requisite;

    @Column(name = "PROFILE_PHOTO")
    private String profilePhoto;

    @Column(name = "CREATED_DATE")
    private Date date;

    @Column(name = "RECIPENT")
    private boolean isRecipent;

    @Column
    @Enumerated(EnumType.STRING)
    private DocumentStatus documentStatus;

    @Column(name = "ROLES")
    @Enumerated(EnumType.STRING)
    private RoleStatus roles;


    @Column(name = "USERS_STATUS")
    @Enumerated(EnumType.STRING)
    private UsersStatus usersStatus;


    @OneToOne(cascade = CascadeType.REMOVE)
    private Bucket bucket;

}
