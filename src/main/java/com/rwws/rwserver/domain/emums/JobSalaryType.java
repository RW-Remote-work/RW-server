package com.rwws.rwserver.domain.emums;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum JobSalaryType implements IEnum<String> {
    MONTHLY_PAY,
    YEARLY_PAY;

    @Override
    public String getValue() {
        return this.name();
    }
}
