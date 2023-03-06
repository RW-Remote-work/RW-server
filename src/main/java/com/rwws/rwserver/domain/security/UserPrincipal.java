package com.rwws.rwserver.domain.security;

import lombok.Getter;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Getter
public class UserPrincipal implements UserDetails, CredentialsContainer {
    private final Long id;
    private final String login;
    private String password;
    private final String displayName;
    private final String email;
    private final boolean activated = true;
    private final Set<Authority> authorities;

    public UserPrincipal(User user, Set<Authority> authorities) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.displayName = user.getDisplayName();
        this.email = user.getEmail();
        this.authorities = authorities;
    }


    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.activated;
    }
}
