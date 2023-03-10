package com.rwws.rwserver.config;

import com.rwws.rwserver.security.JwtAuthenticationTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final ApplicationProperties properties;
    private final SecurityProblemSupport problemSupport;
    private final UserDetailsService userDetailsService;
    private final SessionRegistry sessionRegistry;
    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    public SecurityConfig(ApplicationProperties properties,
                          SecurityProblemSupport problemSupport,
                          UserDetailsService userDetailsService,
                          SessionRegistry sessionRegistry,
                          JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter) {
        this.properties = properties;
        this.problemSupport = problemSupport;
        this.userDetailsService = userDetailsService;
        this.sessionRegistry = sessionRegistry;
        this.jwtAuthenticationTokenFilter= jwtAuthenticationTokenFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringAntMatchers("/verifycodes", "/register", "/login")
                .and()
                .rememberMe()
                .key(properties.getRememberMeKey())
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
                // 自定义权限拦截器JWT过滤器
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
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
