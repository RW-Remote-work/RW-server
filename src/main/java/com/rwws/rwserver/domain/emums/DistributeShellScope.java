package com.rwws.rwserver.domain.emums;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum DistributeShellScope implements IEnum<String> {
    ALL_USER, TARGET_USER;

    @Override
    public String getValue() {
        return this.name();
    }
}
