package com.cherimoya.cherimoya.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleStatus implements GrantedAuthority  {
    USER,
    RECIPIENT,
    MODER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
