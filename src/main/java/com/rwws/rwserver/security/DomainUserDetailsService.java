package com.rwws.rwserver.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rwws.rwserver.domain.security.User;
import com.rwws.rwserver.domain.security.UserAuthority;
import com.rwws.rwserver.domain.security.UserPrincipal;
import com.rwws.rwserver.module.system.user.mapper.UserAuthorityMapper;
import com.rwws.rwserver.module.system.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
public class DomainUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;
    private final UserAuthorityMapper userAuthorityMapper;

    public DomainUserDetailsService(UserMapper userMapper,
                                    UserAuthorityMapper userAuthorityMapper) {
        this.userMapper = userMapper;
        this.userAuthorityMapper = userAuthorityMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userMapper.selectOne(
                new QueryWrapper<User>()
                        .eq("activated", true)
                        .and(
                                it -> it.eq("login", username)
                                        .or()
                                        .eq("email", username)
                        )
        );
        if (user == null) throw new UsernameNotFoundException("User " + username + " does not exist.");

        var authorities = userAuthorityMapper.selectList(
                new QueryWrapper<UserAuthority>()
                        .eq("user_id", user.getId())
        ).stream().map(UserAuthority::getAuthority).collect(Collectors.toSet());
        return new UserPrincipal(user, authorities);
    }


}
