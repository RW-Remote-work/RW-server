package com.rwws.rwserver.config;

import com.rwws.rwserver.security.JWTAuthenticationFilter;
import com.rwws.rwserver.security.JWTLoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final SecurityProblemSupport problemSupport;
    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final JWTLoginFilter jwtLoginFilter;

    public SecurityConfig(SecurityProblemSupport problemSupport,
                          JWTAuthenticationFilter jwtAuthenticationFilter,
                          JWTLoginFilter jwtLoginFilter) {
        this.problemSupport = problemSupport;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtLoginFilter = jwtLoginFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationConfiguration configuration) throws Exception {
        jwtLoginFilter.setAuthenticationManager(configuration.getAuthenticationManager());
        // @formatter:off
        return http
            .csrf()
                .disable()
            .rememberMe()
                .disable()
            .formLogin()
                .disable()
            .logout()
                .disable()
            .exceptionHandling()
                .authenticationEntryPoint(problemSupport)
                .accessDeniedHandler(problemSupport)
                .and()
            .sessionManagement()
                .disable()
            .addFilterAfter(jwtAuthenticationFilter, AnonymousAuthenticationFilter.class)
            .addFilterAfter(jwtLoginFilter, JWTAuthenticationFilter.class)
                .build();
        // @formatter:on
    }

}
