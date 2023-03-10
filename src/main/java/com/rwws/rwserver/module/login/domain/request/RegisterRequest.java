package com.rwws.rwserver.module.login.domain.request;

import com.rwws.rwserver.common.validation.EmailCheck;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegisterRequest {

    @NotEmpty(message = "邮箱不能为空")
    @EmailCheck(message = "邮箱格式不正确")
    private String email;

    @NotEmpty(message = "验证码不能为空")
    private String code;

    @NotEmpty(message = "密码不能为空")
    private String password;
}
