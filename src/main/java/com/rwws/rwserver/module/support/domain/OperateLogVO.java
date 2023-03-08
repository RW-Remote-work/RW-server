package com.rwws.rwserver.module.support.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 *  操作日志信息
 *
 */
@Data
public class OperateLogVO {

    @Schema(name = "主键")
    private Long operateLogId;

    @Schema(name = "用户id")
    private Long operateUserId;

    private Integer operateUserType;

    @Schema(name = "用户名称")
    private String operateUserName;

    @Schema(name = "操作模块")
    private String module;

    @Schema(name = "操作内容")
    private String content;

    @Schema(name = "请求路径")
    private String url;

    @Schema(name = "请求方法")
    private String method;

    @Schema(name = "请求参数")
    private String param;

    @Schema(name = "客户ip")
    private String ip;

    @Schema(name = "user-agent")
    private String userAgent;

    @Schema(name = "请求结果 0失败 1成功")
    private Boolean successFlag;

    @Schema(name = "失败原因")
    private String failReason;

    @Schema(name = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @Schema(name = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;


}
