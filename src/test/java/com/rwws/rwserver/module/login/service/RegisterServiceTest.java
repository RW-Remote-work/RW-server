package com.rwws.rwserver.module.login.service;


import com.rwws.rwserver.BaseTest;
import com.rwws.rwserver.controller.request.RegisterRequest;
import com.rwws.rwserver.controller.response.RegisterResponse;
import com.rwws.rwserver.exception.BadRequestProblem;
import com.rwws.rwserver.service.RegisterService;
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
        Assertions.assertFalse(registerResponse.getToken().isEmpty());
    }

    @Test
    public void testUserExists() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("wublhappy@hotmail.com");
        registerRequest.setCode("151263");
        Assertions.assertThrows(BadRequestProblem.class, () -> registerService.register(registerRequest));
    }
}
