package com.rwws.rwserver.common.enumer;

/**
 * 用户类型
 *
 */
public enum UserTypeEnum implements BaseEnum {

    /**
     * 管理端 员工用户
     */
    ADMIN_EMPLOYEE(1, "员工");

    private Integer type;

    private String desc;

    UserTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return type;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
