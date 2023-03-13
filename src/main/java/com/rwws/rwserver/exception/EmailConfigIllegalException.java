package com.rwws.rwserver.exception;

public class EmailConfigIllegalException extends RuntimeException {

    public EmailConfigIllegalException() {
        super("邮箱配置异常");
    }
}
