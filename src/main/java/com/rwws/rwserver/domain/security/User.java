package com.rwws.rwserver.domain.security;

import com.baomidou.mybatisplus.annotation.TableId;
import com.rwws.rwserver.domain.AbstractAuditingEntity;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;

@Data
public class User extends AbstractAuditingEntity {
    @TableId
    private Long id;
    private String login;
    private String password;
    private String displayName;
    private String email;
    private boolean activated = true;

    @Nullable
    private String lastLoginIp;

    @Nullable
    private Instant lastLoginTime;
}
