package com.rwws.rwserver.module.system.login.controller;

import cn.hutool.core.util.StrUtil;
import com.rwws.rwserver.exception.BadRequestProblem;
import com.rwws.rwserver.module.system.login.domain.request.LoginRequest;
import com.rwws.rwserver.module.system.login.domain.response.LoginResponse;
import com.rwws.rwserver.module.system.login.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sessions")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    public LoginResponse login(@Validated @RequestBody LoginRequest loginRequest) {
        if (StrUtil.isEmpty(loginRequest.getEmail()) || StrUtil.isEmpty(loginRequest.getPassword()))
            throw new BadRequestProblem("Email or password cannot be empty");
        return this.loginService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }
}
