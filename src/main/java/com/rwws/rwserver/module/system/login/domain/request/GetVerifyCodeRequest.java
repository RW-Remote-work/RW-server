package com.rwws.rwserver.module.system.login.domain.request;

import com.rwws.rwserver.common.validation.EmailCheck;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class GetVerifyCodeRequest {

    @NotEmpty(message = "邮箱不能为空")
    @EmailCheck(message = "邮箱格式不正确")
    private String email;
}
