package com.rwws.rwserver.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rwws.rwserver.domain.security.User;
import com.rwws.rwserver.domain.security.UserAuthority;
import com.rwws.rwserver.mapper.UserAuthorityMapper;
import com.rwws.rwserver.mapper.UserMapper;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.rwws.rwserver.domain.security.Authority.SUPER_ADMIN;

@Component
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    private final ApplicationProperties properties;
    private final UserMapper userMapper;
    private final UserAuthorityMapper userAuthorityMapper;
    private final PasswordEncoder encoder;

    public StartupApplicationListener(ApplicationProperties properties,
                                      UserMapper userMapper,
                                      UserAuthorityMapper userAuthorityMapper,
                                      PasswordEncoder encoder) {
        this.properties = properties;
        this.userMapper = userMapper;
        this.userAuthorityMapper = userAuthorityMapper;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        createSuperAdmin();
    }

    private void createSuperAdmin() {
        var exists = userMapper.exists(
                new QueryWrapper<User>()
                        .ge("login", "admin")
                        .ge("email", "admin")
        );
        if (!exists) {
            var superAdmin = new User();
            superAdmin.setLogin("RwSuperAdmin");
            superAdmin.setEmail("RwSuperAdmin");
            superAdmin.setPassword(encoder.encode(properties.getSuperAdminPassword()));
            superAdmin.setDisplayName("RwSuperAdmin");
            superAdmin.setCreatedBy("Auto Create");
            userMapper.insert(superAdmin);

            var authority = new UserAuthority();
            authority.setUserId(superAdmin.getId());
            authority.setAuthority(SUPER_ADMIN);
            userAuthorityMapper.insert(authority);
        }
    }
}
