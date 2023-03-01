package com.rwws.rwserver.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rwws.rwserver.domain.security.User;
import com.rwws.rwserver.mapper.security.UserMapper;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.rwws.rwserver.domain.security.Authority.SUPER_ADMIN;

@Component
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    private final ApplicationProperties properties;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    public StartupApplicationListener(ApplicationProperties properties,
                                      UserMapper userMapper,
                                      PasswordEncoder encoder) {
        this.properties = properties;
        this.userMapper = userMapper;
        this.encoder = encoder;
    }

    @Override
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
            superAdmin.setAuthorities(Set.of(SUPER_ADMIN));
            superAdmin.setCreatedBy("Auto Create");
            userMapper.insert(superAdmin);
        }
    }
}
