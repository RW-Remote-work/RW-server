package com.rwws.rwserver.module.login.controller;

import com.rwws.rwserver.module.login.domain.request.RegisterRequest;
import com.rwws.rwserver.module.login.domain.response.RegisterResponse;
import com.rwws.rwserver.module.login.service.RegisterService;
import com.rwws.rwserver.module.support.annotation.WebOperateLog;
<<<<<<< HEAD:src/main/java/com/rwws/rwserver/module/system/login/controller/RegisterController.java
import com.rwws.rwserver.module.system.login.domain.request.RegisterRequest;
import com.rwws.rwserver.module.system.login.domain.response.RegisterResponse;
import com.rwws.rwserver.module.system.login.service.RegisterService;
import org.springframework.http.HttpStatus;
=======
>>>>>>> 74d04db4d0194091fff91e6653b65ca71fa4afb5:src/main/java/com/rwws/rwserver/module/login/controller/RegisterController.java
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@WebOperateLog
public class RegisterController {

    private RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public RegisterResponse register(@Validated @RequestBody RegisterRequest request) {
        return this.registerService.register(request);
    }

    @PutMapping
    public RegisterResponse update(@Validated @RequestBody RegisterRequest request) {
        return null;
    }
}
