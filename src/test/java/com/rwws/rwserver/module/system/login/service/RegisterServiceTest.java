package com.rwws.rwserver.module.system.login.service;

import cn.hutool.core.util.StrUtil;
import com.rwws.rwserver.BaseTest;
import com.rwws.rwserver.exception.BadRequestProblem;
import com.rwws.rwserver.module.system.login.domain.request.RegisterRequest;
import com.rwws.rwserver.module.system.login.domain.response.RegisterResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RegisterServiceTest extends BaseTest {

    @Autowired
    private RegisterService registerService;


    @Test
    public void testRegister() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("wublhappy@hotmail.com");
        registerRequest.setCode("390282");
        RegisterResponse registerResponse = this.registerService.register(registerRequest);
        Assertions.assertTrue(registerResponse != null);
        Assertions.assertTrue(StrUtil.isNotEmpty(registerResponse.getToken()));
        System.out.println(String.format("Token {0}", registerResponse.getToken()));
    }

    @Test
    public void testUserExists() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("wublhappy@hotmail.com");
        registerRequest.setCode("151263");
        Assertions.assertThrows(BadRequestProblem.class, () -> registerService.register(registerRequest));
    }
}
