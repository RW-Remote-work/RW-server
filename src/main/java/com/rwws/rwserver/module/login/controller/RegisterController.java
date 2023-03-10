package com.rwws.rwserver.module.login.controller;

import com.rwws.rwserver.module.login.domain.request.RegisterRequest;
import com.rwws.rwserver.module.login.domain.response.RegisterResponse;
import com.rwws.rwserver.module.login.service.RegisterService;
import com.rwws.rwserver.module.support.annotation.WebOperateLog;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@WebOperateLog
public class RegisterController {

    private RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    public RegisterResponse register(@Validated @RequestBody RegisterRequest request) {
        return this.registerService.register(request);
    }
}
