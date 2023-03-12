package com.rwws.rwserver.security;

import com.rwws.rwserver.config.JWTProperties;
import com.rwws.rwserver.service.JWTTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * JWT登录授权过滤器
 */
@Slf4j
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private static final Set<String> SKIP_URI = Set.of(JWTLoginFilter.LOGIN_URI);
    private final UserDetailsService userDetailsService;
    private final JWTTokenService jwtTokenService;
    private final JWTProperties properties;
    protected AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();

    public JWTAuthenticationFilter(UserDetailsService userDetailsService,
                                   JWTTokenService jwtTokenService,
                                   JWTProperties properties) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenService = jwtTokenService;
        this.properties = properties;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        if (SKIP_URI.contains(request.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }
        var authHeader = request.getHeader(properties.getTokenHeader());
        if (authHeader != null && authHeader.startsWith(properties.getTokenHead())) {
            var authToken = authHeader.substring(properties.getTokenHead().length());// The part after "Bearer "
            if (jwtTokenService.isExpired(authToken)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            var username = jwtTokenService.getUserNameFromToken(authToken);
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                var userDetails = userDetailsService.loadUserByUsername(username);
                var authentication = UsernamePasswordAuthenticationToken.authenticated(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(authenticationDetailsSource.buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}
