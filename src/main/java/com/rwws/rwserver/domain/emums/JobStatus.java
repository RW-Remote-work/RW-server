package com.rwws.rwserver.domain.emums;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum JobStatus implements IEnum<String> {
    PENDING_REVIEW,
    ONLINE,
    OFFLINE,
    DENY;

    @Override
    public String getValue() {
        return this.name();
    }
}
