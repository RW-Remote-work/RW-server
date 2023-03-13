package com.rwws.rwserver.service;


import com.rwws.rwserver.controller.request.SendEmailRequest;
import com.rwws.rwserver.manager.RedisCacheManager;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static com.rwws.rwserver.common.enums.RedisKeyPrefix.EMAIL;

@Service
@Slf4j
public class EmailService {
    private final static Long CODE_EXPIRATION = 300L;

    private final JavaMailSender javaMailSender;
    private final RedisCacheManager redisCacheManager;
    private final MailProperties mailProperties;
    private final Template mailTemplate;

    public EmailService(JavaMailSender javaMailSender,
                        RedisCacheManager redisCacheManager,
                        MailProperties mailProperties,
                        Template mailTemplate) {
        this.javaMailSender = javaMailSender;
        this.redisCacheManager = redisCacheManager;
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
        send(request);
    }

    /**
     * 发送邮件
     *
     * @param request
     */
    @Async
    public void send(SendEmailRequest request) {
        try {
            var mailMessage = javaMailSender.createMimeMessage();
            var code = ThreadLocalRandom.current().nextInt(0, 6);
            var writer = new StringWriter();
            mailTemplate.process(Map.of("code", code), writer);

            var helper = new MimeMessageHelper(mailMessage, true);
            helper.setFrom(mailProperties.getUsername());
            helper.setTo(request.getTo());
            helper.setSubject(request.getSubject());
            helper.setText(writer.toString(), true);
            javaMailSender.send(mailMessage);
            var key = EMAIL.format(request.getTo());
            redisCacheManager.set(key, Duration.ofSeconds(CODE_EXPIRATION));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
