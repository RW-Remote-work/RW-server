package com.rwws.rwserver.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.Instant;

@Data
public class LoginRecord {
    @TableId
    private Long id;
    private Long userId;
    private String ip;
    private Instant time;

}
