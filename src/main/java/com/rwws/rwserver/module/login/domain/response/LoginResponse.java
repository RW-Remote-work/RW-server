package com.rwws.rwserver.module.login.domain.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginResponse {

    private RegisterResponse.UserVO userVO;

    @Schema(title = "会话Token")
    private String token;
}
