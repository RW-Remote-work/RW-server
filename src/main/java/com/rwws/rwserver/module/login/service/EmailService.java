package com.rwws.rwserver.module.login.service;


import com.rwws.rwserver.common.constant.RedisKeyConstant;
import com.rwws.rwserver.module.login.domain.request.SendEmailRequest;
import com.rwws.rwserver.service.RedisService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
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
        var mailMessage = this.javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        var code = ThreadLocalRandom.current().nextInt(0, 6);

        try {
            MimeMessage mailMessage = this.javaMailSender.createMimeMessage();
            MimeMessageHelper helper;
            String randomCode = RandomUtil.randomNumbers(6);
            TemplateEngine templateEngine = TemplateUtil.createEngine(new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH));
            Template template = templateEngine.getTemplate("mail-template.ftl");
            String content = template.render(MapUtil.of("code", randomCode));
            helper = new MimeMessageHelper(mailMessage, true);
            helper.setFrom(this.username);
            helper.setTo(emailDTO.getToEmail());
            helper.setSubject(emailDTO.getSubject());
            helper.setText(content, true);
            this.javaMailSender.send(mailMessage);
            log.info("Verification code {} email sent successfully", randomCode);
            String redisKey = this.redisService.generateRedisKey(RedisKeyConstant.Module.EMAIL_CODE, emailDTO.getToEmail());
            this.redisService.set(redisKey, randomCode, this.codeExpiration);
        } catch (MessagingException e) {
            log.error(e.getMessage(), e);
        } catch (MailException ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
