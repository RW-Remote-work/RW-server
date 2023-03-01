package com.rwws.rwserver.domain.security;

import lombok.Data;

@Data
public class UserAuthority {

    private Long userId;

    private Authority authority;
}
