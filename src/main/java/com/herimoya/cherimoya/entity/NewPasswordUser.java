package com.herimoya.cherimoya.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewPasswordUser {
    private String userEmail;
    private Integer token;
    private String password;
}