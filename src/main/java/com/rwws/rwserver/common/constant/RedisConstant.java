package com.rwws.rwserver.common.constant;

public class RedisConstant {

    public static final String EMAIL_CODE_PREFIX = "EMAIL_CODE#";


    public static String getEmailCodeKey(String emailCode) {
        return EMAIL_CODE_PREFIX + emailCode;
    }

}
