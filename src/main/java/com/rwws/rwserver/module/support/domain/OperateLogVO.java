package com.rwws.rwserver.module.support.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 *  操作日志信息
 *
 * @Author 1024创新实验室: 罗伊
 * @Date 2021-12-08 20:48:52
 * @Wechat zhuoda1024
 * @Email lab1024@163.com
 * @Copyright 1024创新实验室 （ https://1024lab.net ）
 */
@Data
public class OperateLogVO {

    private Long operateLogId;

    private Long operateUserId;

    private Integer operateUserType;

    private String operateUserName;

    private String module;

    private String content;

    private String url;

    private String method;

    private String param;

    private String ip;

    private String userAgent;

    private Boolean successFlag;

    private String failReason;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;


}
