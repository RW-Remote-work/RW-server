package com.rwws.rwserver.domain.security;

import com.baomidou.mybatisplus.annotation.TableId;
import com.rwws.rwserver.domain.AbstractAuditingEntity;
import lombok.Data;

@Data
public class User extends AbstractAuditingEntity {
    @TableId
    private Long id;
    private String login;
    private String password;
    private String displayName;
    private String email;
    private boolean activated = true;
}
