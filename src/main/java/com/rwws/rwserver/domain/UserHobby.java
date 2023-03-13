package com.rwws.rwserver.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class UserHobby {
    @TableId
    private Long id;
    private Long hobbyId;
    private Long userId;
}
