package com.rwws.rwserver.domain.security;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.rwws.rwserver.domain.AbstractAuditingEntity;
import lombok.Data;

import java.util.Set;

@Data
public class User extends AbstractAuditingEntity {
    @TableId
    private Long id;
    private String login;
    private String password;
    private String displayName;
    private String email;
    private boolean activated = true;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private Set<Authority> authorities = Set.of();
}
