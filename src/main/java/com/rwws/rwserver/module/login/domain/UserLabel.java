package com.rwws.rwserver.module.login.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class UserLabel {

    @TableId
    private Long id;

    private Long userId;

    private String label;
}
