package com.rwws.rwserver.module.system.login.service;

import com.rwws.rwserver.BaseTest;
import com.rwws.rwserver.module.system.login.domain.dto.EmailDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailServiceTest extends BaseTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void testSend() {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setToEmail("wublhappy@hotmail.com");
        emailDTO.setSubject("RW社区邮箱验证码");
        this.emailService.send(emailDTO);
    }
}
