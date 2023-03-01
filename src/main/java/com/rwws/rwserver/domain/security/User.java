package com.rwws.rwserver.domain.security;

import com.rwws.rwserver.domain.AbstractAuditingEntity;
import lombok.Data;

@Data
public class User extends AbstractAuditingEntity {
    private Long id;
    private String login;
    private String password;
    private String displayName;
    private String email;
    private boolean activated = true;
}
