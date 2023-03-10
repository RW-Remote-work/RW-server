package com.rwws.rwserver.module.system.login.domain.response;

import lombok.Data;

@Data
public class RegisterResponse {

    private UserVO userVO;

    @Data
    public static class UserVO {

        private String token;
    }
}
