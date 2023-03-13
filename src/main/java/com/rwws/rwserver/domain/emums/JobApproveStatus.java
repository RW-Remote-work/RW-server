package com.rwws.rwserver.domain.emums;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum JobApproveStatus implements IEnum<String> {
    ADOPT, REJECT;

    @Override
    public String getValue() {
        return this.name();
    }

}
