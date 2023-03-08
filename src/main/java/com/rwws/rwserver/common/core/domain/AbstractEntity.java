package com.rwws.rwserver.common.core.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.Instant;

import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT;
import static com.baomidou.mybatisplus.annotation.FieldFill.UPDATE;

@Data
public abstract class AbstractEntity {

    @TableField(fill = INSERT)
    private String createdBy;

    @TableField(fill = INSERT)
    private Instant createdDate;

    @TableField(fill = UPDATE)
    private String lastModifiedBy;

    @TableField(fill = UPDATE)
    private Instant lastModifiedDate;

}
