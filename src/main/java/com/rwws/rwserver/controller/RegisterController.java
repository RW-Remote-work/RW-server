package com.rwws.rwserver.controller;

import com.rwws.rwserver.controller.request.RegisterRequest;
import com.rwws.rwserver.service.RegisterService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    @Transactional
    public void register(@Validated @RequestBody RegisterRequest request) {
        registerService.register(request);
    }

    @PutMapping
    public void update(@Validated @RequestBody RegisterRequest request) {

    }
}
