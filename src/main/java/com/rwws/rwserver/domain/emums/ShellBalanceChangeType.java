package com.rwws.rwserver.domain.emums;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum ShellBalanceChangeType implements IEnum<String> {
    PLUS, MINUS;

    @Override
    public String getValue() {
        return this.name();
    }
}
