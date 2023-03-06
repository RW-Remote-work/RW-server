package com.rwws.rwserver.domain.security;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class UserAuthority {
    @TableId
    private Long id;

    private Long userId;

    private Authority authority;
}
