package com.rwws.rwserver.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rwws.rwserver.config.JWTProperties;
import com.rwws.rwserver.service.JWTTokenService;
import lombok.Data;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.zalando.problem.Status.*;

@Component
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
    public static final String LOGIN_URI = "/api/authentication";

    private final JWTTokenService jwtTokenService;
    private final JWTProperties properties;
    private final ObjectMapper objectMapper;


    public JWTLoginFilter(JWTTokenService jwtTokenService,
                          JWTProperties properties,
                          ObjectMapper objectMapper) {
        super(LOGIN_URI);
        this.jwtTokenService = jwtTokenService;
        this.properties = properties;
        this.objectMapper = objectMapper;
        setAuthenticationSuccessHandler((request, response, authentication) -> response.setStatus(OK.getStatusCode()));
        setAuthenticationFailureHandler((request, response, exception) -> response.setStatus(UNAUTHORIZED.getStatusCode()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (LOGIN_URI.equals(request.getRequestURI())) {
            if (!request.getMethod().equals("POST")) {
                buildResponse(response, BAD_REQUEST.getStatusCode(),
                        BAD_REQUEST.getReasonPhrase(),
                        "authentication method not supported: " + request.getMethod());
                return null;
            }
            var usernames = request.getParameterValues("username");
            var passwords = request.getParameterValues("password");
            if (usernames.length == 0 || passwords.length == 0) {
                buildResponse(response, UNAUTHORIZED.getStatusCode(),
                        UNAUTHORIZED.getReasonPhrase(),
                        "username or password must not be null");
                return null;
            }

            var authentication = UsernamePasswordAuthenticationToken.unauthenticated(usernames[0], passwords[0]);
            authentication.setDetails(this.authenticationDetailsSource.buildDetails(request));
            var token = jwtTokenService.generateToken(usernames[0]);
            response.addHeader(HttpHeaders.AUTHORIZATION, properties.getTokenHead() + token);
            response.setStatus(OK.getStatusCode());

            return this.getAuthenticationManager().authenticate(authentication);
        }
        return null;
    }

    private void buildResponse(HttpServletResponse response,
                               int sc,
                               String title,
                               @Nullable String detail) throws IOException {
        var loginResponse = new LoginResponse();
        loginResponse.setTitle(title);
        loginResponse.setDetail(detail);
        response.setStatus(sc);
        objectMapper.writeValue(response.getOutputStream(), loginResponse);
    }

    @Override
    public void afterPropertiesSet() {
    }


    @Data
    private static class LoginResponse {
        private String title;
        private String detail;
    }
}
