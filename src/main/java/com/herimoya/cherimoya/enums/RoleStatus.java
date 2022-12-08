package com.herimoya.cherimoya.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleStatus implements GrantedAuthority {
    USER,
    RECIPIENT,
    GUEST,
    MODER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}