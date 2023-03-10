package com.rwws.rwserver.module.login.controller;

<<<<<<< HEAD:src/main/java/com/rwws/rwserver/module/system/login/controller/VerifyCodeController.java
import com.rwws.rwserver.module.system.login.domain.request.GetVerifyCodeRequest;
import com.rwws.rwserver.module.system.login.service.EmailService;
import org.springframework.http.HttpStatus;
=======
import com.rwws.rwserver.module.login.domain.request.GetVerifyCodeRequest;
import com.rwws.rwserver.module.login.service.EmailService;
>>>>>>> 74d04db4d0194091fff91e6653b65ca71fa4afb5:src/main/java/com/rwws/rwserver/module/login/controller/VerifyCodeController.java
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verifycodes")
public class VerifyCodeController {

    private EmailService emailService;

    public VerifyCodeController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void getVerifyCode(@Validated @RequestBody GetVerifyCodeRequest verifyCodeRequest) {
        this.emailService.sendVerifyCodeEmail(verifyCodeRequest.getEmail());
    }
}
