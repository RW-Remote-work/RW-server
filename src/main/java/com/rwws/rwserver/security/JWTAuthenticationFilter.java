package com.rwws.rwserver.security;

import com.rwws.rwserver.domain.security.Authority;
import com.rwws.rwserver.manager.JWTManager;
import com.rwws.rwserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
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
import java.time.Instant;
import java.util.Set;

import static com.auth0.jwt.RegisteredClaims.EXPIRES_AT;
import static com.auth0.jwt.RegisteredClaims.SUBJECT;

/**
 * JWT登录授权过滤器
 */
@Slf4j
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private static final Set<String> SKIP_URI = Set.of(JWTLoginFilter.LOGIN_URI);
    private final UserDetailsService userDetailsService;
    private final JWTManager jwtManager;
    private final UserService userService;
    private final AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();

    public JWTAuthenticationFilter(UserDetailsService userDetailsService,
                                   JWTManager jwtManager,
                                   UserService userService) {
        this.userDetailsService = userDetailsService;
        this.jwtManager = jwtManager;
        this.userService = userService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        if (SKIP_URI.contains(request.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }
        var authToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authToken != null) {
            var claims = jwtManager.getClaims(authToken);
            if (claims.get(EXPIRES_AT).asInstant().isBefore(Instant.now())) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            var username = claims.get(SUBJECT).asString();
            boolean activated = userService.isActivated(username);
            if (!activated) {
                //用户激活状态可能被修改为未激活, 注销当前认证状态
                SecurityContextHolder.getContext().setAuthentication(null);
                chain.doFilter(request, response);
                return;
            }
            var currentAuthentication = SecurityContextHolder.getContext().getAuthentication();
            if (currentAuthentication == null || currentAuthentication.getAuthorities()
                    .stream().anyMatch(it -> it.getAuthority().equals(Authority.ROLE_ANONYMOUS.getAuthority()))) {
                var userDetails = userDetailsService.loadUserByUsername(username);
                var authentication = UsernamePasswordAuthenticationToken.authenticated(userDetails, null,
                        userDetails.getAuthorities());
                authentication.setDetails(authenticationDetailsSource.buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}
