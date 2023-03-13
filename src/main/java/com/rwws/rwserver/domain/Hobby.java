package com.rwws.rwserver.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Hobby {
    @TableId
    private Long id;
    private String name;
}
