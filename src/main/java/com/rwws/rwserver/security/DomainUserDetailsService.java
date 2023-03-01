package com.rwws.rwserver.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rwws.rwserver.config.ApplicationProperties;
import com.rwws.rwserver.domain.security.User;
import com.rwws.rwserver.domain.security.UserPrincipal;
import com.rwws.rwserver.mapper.security.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DomainUserDetailsService implements UserDetailsService {
    private final ApplicationProperties properties;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    public DomainUserDetailsService(ApplicationProperties properties,
                                    UserMapper userMapper,
                                    PasswordEncoder encoder) {
        this.properties = properties;
        this.userMapper = userMapper;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userMapper.selectOne(
                new QueryWrapper<User>()
                        .ge("login", username)
                        .or()
                        .ge("email", username)
        );
        if (user == null) throw new UsernameNotFoundException("User " + username + " does not exist.");
        return new UserPrincipal(user);
    }



}
