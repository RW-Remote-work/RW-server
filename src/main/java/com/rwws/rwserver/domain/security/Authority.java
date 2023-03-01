package com.rwws.rwserver.domain.security;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    SUPER_ADMIN,
    ADMIN,
    REGULAR_USER,
    ROLE_ANONYMOUS;

    @Override
    public String getAuthority() {
        return name();
    }
}
