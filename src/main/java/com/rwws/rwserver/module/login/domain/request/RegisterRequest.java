package com.rwws.rwserver.module.login.domain.request;

import com.rwws.rwserver.common.constant.RegexConstant;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegisterRequest {

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确", regexp = RegexConstant.EMAIL)
    private String email;

    @NotBlank(message = "验证码不能为空")
    private String code;

    @NotBlank(message = "密码不能为空")
    private String password;
}
