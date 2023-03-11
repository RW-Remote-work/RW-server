package com.rwws.rwserver.controller;


import com.rwws.rwserver.controller.request.GetVerifyCodeRequest;
import com.rwws.rwserver.service.EmailService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verifycodes")
public class VerifyCodeController {

    private EmailService emailService;

    public VerifyCodeController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public void getVerifyCode(@Validated @RequestBody GetVerifyCodeRequest verifyCodeRequest) {
        this.emailService.sendVerifyCode(verifyCodeRequest.getEmail());
    }
}
