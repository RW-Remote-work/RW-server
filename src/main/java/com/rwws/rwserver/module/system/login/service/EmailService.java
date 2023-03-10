package com.rwws.rwserver.module.system.login.service;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.rwws.rwserver.common.constant.RedisKeyConstant;
import com.rwws.rwserver.common.constant.RegexPatternFactory;
import com.rwws.rwserver.module.support.service.RedisService;
import com.rwws.rwserver.module.system.login.domain.dto.EmailDTO;
import com.rwws.rwserver.module.system.login.exception.EmailConfigIllegalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;

@Service
@Slf4j
public class EmailService {
    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String passsword;

    @Value("${spring.mail.code.expiration}")
    private Long codeExpiration;

    private JavaMailSender javaMailSender;
    private ThreadPoolTaskExecutor taskExecutor;
    private RedisService redisService;

    public EmailService(JavaMailSender javaMailSender,
                        RedisService redisService,
                        @Qualifier("mailThreadPool") ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.javaMailSender = javaMailSender;
        this.taskExecutor = threadPoolTaskExecutor;
        this.redisService = redisService;
    }

    /**
     * 校验Email格式
     * @param email
     * @return
     */
    public boolean checkEmail(String email) {
        if (Objects.isNull(email))
            return false;

        Matcher matcher = RegexPatternFactory.emailPattern().matcher(email);
        return matcher.find();
    }

    /**
     * 发送验证码邮件
     * @param email
     */
    public void sendVerifyCodeEmail(String email) {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setToEmail(email);
        emailDTO.setSubject("RW社区验证码");
        this.send(emailDTO);
    }

    /**
     * 发送邮件
     * @param emailDTO
     */
    public void send(EmailDTO emailDTO) {
        if (Objects.isNull(host) || Objects.isNull(username) || Objects.isNull(passsword))
            throw new EmailConfigIllegalException();

        MimeMessage mailMessage = this.javaMailSender.createMimeMessage();
//        this.javaMailSender.send(StandardCharsets.UTF_8.toString());
        MimeMessageHelper helper;
        String randomCode = RandomUtil.randomNumbers(6);
        try {
            TemplateEngine templateEngine = TemplateUtil.createEngine(new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH));
            Template template = templateEngine.getTemplate("mail-template.ftl");
            String content = template.render(MapUtil.of("code", randomCode));
            helper = new MimeMessageHelper(mailMessage, true);
            helper.setFrom(this.username);
            helper.setTo(emailDTO.getToEmail());
            helper.setSubject(emailDTO.getSubject());
            helper.setText(content, true);
        } catch (MessagingException e) {
            log.error(e.getMessage(), e);
        }

        this.taskExecutor.execute(() -> {
            try {
                this.javaMailSender.send(mailMessage);
                String redisKey = this.redisService.generateRedisKey(RedisKeyConstant.Module.EMAIL_CODE, emailDTO.getToEmail());
                this.redisService.set(redisKey, randomCode, this.codeExpiration);
            } catch (MailException ex) {
                log.error(ex.getMessage(), ex);
            }
        });
    }
}
