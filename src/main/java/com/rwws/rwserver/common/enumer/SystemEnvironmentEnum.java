package com.rwws.rwserver.common.enumer;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统环境枚举类
 *
 */
@AllArgsConstructor
@Getter
public enum SystemEnvironmentEnum implements BaseEnum {
    /**
     * dev
     */
    DEV(SystemEnvironmentNameConst.DEV, "开发环境"),

    /**
     * sit
     */
    TEST(SystemEnvironmentNameConst.SIT, "测试环境"),

    /**
     * pre
     */
    PRE(SystemEnvironmentNameConst.PRE, "预发布环境"),

    /**
     * prod
     */
    PROD(SystemEnvironmentNameConst.PROD, "生产环境");


    private final String value;

    private final String desc;

    public static final class SystemEnvironmentNameConst {
        public static final String DEV = "dev";
        public static final String SIT = "sit";
        public static final String PRE = "pre";
        public static final String PROD = "prod";
    }

}
