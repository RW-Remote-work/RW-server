package com.rwws.rwserver.controller.request;

import lombok.Data;

@Data
public class SendEmailRequest {

    /**
     * 目的邮箱
     */
    private String to;

    /**
     * 邮件主题
     */
    private String subject;
}
