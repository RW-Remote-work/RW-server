package com.rwws.rwserver.module.system.login.controller;

import com.rwws.rwserver.module.support.annotation.WebOperateLog;
import com.rwws.rwserver.module.system.login.domain.request.RegisterRequest;
import com.rwws.rwserver.module.system.login.domain.response.RegisterResponse;
import com.rwws.rwserver.module.system.login.service.EmailService;
import com.rwws.rwserver.module.system.login.service.RegisterService;
import com.rwws.rwserver.module.user.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
