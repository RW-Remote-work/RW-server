package com.rwws.rwserver.domain.emums;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum ShellSource implements IEnum<String> {
    EARN, PURCHASE, PLATFORM_DISTRIBUTION;

    @Override
    public String getValue() {
        return this.name();
    }

}
