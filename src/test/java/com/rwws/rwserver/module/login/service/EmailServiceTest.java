package com.rwws.rwserver.module.login.service;

import com.rwws.rwserver.BaseTest;
import com.rwws.rwserver.controller.request.SendEmailRequest;
import com.rwws.rwserver.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailServiceTest extends BaseTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void testSend() {
        SendEmailRequest sendEmailRequest = new SendEmailRequest();
        sendEmailRequest.setTo("wublhappy@hotmail.com");
        sendEmailRequest.setSubject("RW社区邮箱验证码");
        this.emailService.send(sendEmailRequest);
        try {
            Thread.sleep(20 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
