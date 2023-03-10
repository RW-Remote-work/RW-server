package com.rwws.rwserver.module.system.login.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rwws.rwserver.common.constant.ZoneIdConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterResponse {

    private UserVO userVO;

    @Schema(title = "会话Token")
    private String token;

    @Data
    public static class UserVO {

        @Schema(title = "登录名")
        private String login;

        @Schema(title = "显示名称")
        private String displayName;

        @Schema(title = "电子邮件")
        private String email;

        @Schema(title = "创建日期")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = ZoneIdConstant.CHINA_STANDARD)
        private LocalDateTime createDate;
    }
}
