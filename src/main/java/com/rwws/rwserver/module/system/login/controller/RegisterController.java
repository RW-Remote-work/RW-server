package com.rwws.rwserver.module.system.login.controller;

import com.rwws.rwserver.module.support.annotation.WebOperateLog;
import com.rwws.rwserver.module.system.login.domain.request.RegisterRequest;
import com.rwws.rwserver.module.system.login.service.EmailService;
import com.rwws.rwserver.module.user.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@WebOperateLog
public class RegisterController {

    private UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void register(@Validated @RequestBody RegisterRequest request) {
    }
}
