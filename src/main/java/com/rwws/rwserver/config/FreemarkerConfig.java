package com.rwws.rwserver.config;

import freemarker.template.Template;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.ui.freemarker.SpringTemplateLoader;


@Configuration
public class FreemarkerConfig {
    @Bean
    public Template mailTemplate() throws Exception {
        var cfg = new freemarker.template.Configuration();
        cfg.setTemplateLoader(new SpringTemplateLoader(new DefaultResourceLoader(), "/templates/"));
        return cfg.getTemplate("mail-template.ftlh");
    }
}
