package com.rwws.rwserver.module.system.login.controller;

import com.rwws.rwserver.module.system.login.domain.response.RegisterResponse;
import com.rwws.rwserver.module.system.login.service.LoginService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }
}
