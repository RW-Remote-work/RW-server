package com.rwws.rwserver.module.login.controller;

import cn.hutool.core.util.StrUtil;
import com.rwws.rwserver.exception.BadRequestProblem;
import com.rwws.rwserver.module.login.domain.response.LoginResponse;
import com.rwws.rwserver.module.login.service.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/slogin")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("")
    public LoginResponse login(@RequestParam String email, @RequestParam String password) {
        if (StrUtil.isEmpty(email) || StrUtil.isEmpty(password))
            throw new BadRequestProblem("Email or password cannot be empty");
        return this.loginService.login(email, password);
    }
}
