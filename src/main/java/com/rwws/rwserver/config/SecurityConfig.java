package com.rwws.rwserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private static final String REMEMBER_ME_KEY = "CNpNhLLkeAvjkaJLZm6BTFvegLRvbdkzud4dUv2LvjFfEmCuzK2RjH22eBnY9Vwpjb2gDcvNSfMQ3Em4CB4Ykhg5TAcxCXncW9y";
    private final SecurityProblemSupport problemSupport;
    private final UserDetailsService userDetailsService;
    private final SessionRegistry sessionRegistry;

    public SecurityConfig(SecurityProblemSupport problemSupport,
                          UserDetailsService userDetailsService,
                          SessionRegistry sessionRegistry) {
        this.problemSupport = problemSupport;
        this.userDetailsService = userDetailsService;
        this.sessionRegistry = sessionRegistry;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .rememberMe()
                .key(REMEMBER_ME_KEY)
                .userDetailsService(userDetailsService)
                .and()
                .formLogin()
                .loginProcessingUrl("/api/authentication")
                .successHandler(this::onAuthenticationSuccess)
                .failureHandler(this::onAuthenticationFailure)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/api/logout")
                .logoutSuccessHandler(this::onLogoutSuccess)
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(problemSupport)
                .accessDeniedHandler(problemSupport)
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .sessionRegistry(sessionRegistry)
                .expiredSessionStrategy(this::onSessionExpired)
                .and()
                .and()
                .build();
    }

    private void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                         Authentication authentication) {
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                         AuthenticationException exception) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
    }

    private void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                 Authentication authentication) {
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private void onSessionExpired(SessionInformationExpiredEvent event) throws IOException {
        var response = event.getResponse();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter()
                // language=JSON
                .print("""
                        {
                          "status": 401,
                          "message": "This session has been expired."
                        }""");
        response.setStatus(401);
        response.flushBuffer();
    }
}
