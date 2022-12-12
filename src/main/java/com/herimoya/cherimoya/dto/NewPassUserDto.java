package com.herimoya.cherimoya.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewPassUserDto {
    private String login;
    private String email;
    private String password;
    private String repeatPassword;
    private Integer token;
}