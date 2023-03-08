package com.rwws.rwserver.module.system.login.domain.dto;

import lombok.Data;

@Data
public class EmailDTO {

    /**
     * 目的邮箱
     */
    private String toEmail;

    /**
     * 邮件主题
     */
    private String subject;
}
