package com.rwws.rwserver.module.login.service;


import com.rwws.rwserver.common.constant.RedisKeyConstant;
import com.rwws.rwserver.module.login.domain.request.SendEmailRequest;
import com.rwws.rwserver.service.RedisService;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class EmailService {
    private final static Long CODE_EXPIRATION = 300L;

    private final JavaMailSender javaMailSender;
    private final RedisService redisService;
    private final MailProperties mailProperties;
    private final Template mailTemplate;

    public EmailService(JavaMailSender javaMailSender,
                        RedisService redisService,
                        MailProperties mailProperties,
                        Template mailTemplate) {
        this.javaMailSender = javaMailSender;
        this.redisService = redisService;
        this.mailProperties = mailProperties;
        this.mailTemplate = mailTemplate;
    }

    /**
     * 发送验证码邮件
     *
     * @param email
     */
    public void sendVerifyCode(String email) {
        var request = new SendEmailRequest();
        request.setTo(email);
        request.setSubject("RW社区验证码");
        this.send(request);
    }

    /**
     * 发送邮件
     *
     * @param request
     */
    @Async
    public void send(SendEmailRequest request) {
        try {
            var mailMessage = this.javaMailSender.createMimeMessage();
            var code = ThreadLocalRandom.current().nextInt(0, 6);
            var writer = new StringWriter();
            mailTemplate.process(Map.of("code", code), writer);

            var helper = new MimeMessageHelper(mailMessage, true);
            helper.setFrom(mailProperties.getUsername());
            helper.setTo(request.getTo());
            helper.setSubject(request.getSubject());
            helper.setText(writer.toString(), true);
            this.javaMailSender.send(mailMessage);
            log.info("Verification code {} email sent successfully", code);
            String redisKey = this.redisService.generateRedisKey(RedisKeyConstant.Module.EMAIL_CODE, request.getTo());
            this.redisService.set(redisKey, code, CODE_EXPIRATION);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
