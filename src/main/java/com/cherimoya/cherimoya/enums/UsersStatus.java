package com.cherimoya.cherimoya.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UsersStatus implements GrantedAuthority{
    ACTIVE,
    BANNED,
    DELETED;

    @Override
    public String getAuthority() {
        return name();
    }
}
