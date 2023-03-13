package com.rwws.rwserver.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;

import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT;
import static com.baomidou.mybatisplus.annotation.FieldFill.UPDATE;

@Data
public abstract class AbstractAuditingEntity {
    @TableField(fill = INSERT)
    private String createdBy;

    @TableField(fill = INSERT)
    private Instant createdDate;

    @Nullable
    @TableField(fill = UPDATE)
    private String lastModifiedBy;

    @Nullable
    @TableField(fill = UPDATE)
    private Instant lastModifiedDate;
}
