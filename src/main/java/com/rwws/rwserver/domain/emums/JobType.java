package com.rwws.rwserver.domain.emums;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum JobType implements IEnum<String> {
    REMOTE_FULL_TIME,
    REMOTE_PART_TIME,
    UNDEFINED;

    @Override
    public String getValue() {
        return this.name();
    }
}
