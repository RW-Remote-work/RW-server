package com.rwws.rwserver.config;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.Instant;

@Configuration
public class MybatisPlusConfig {
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                this.strictInsertFill(metaObject, "createdDate", Instant.class, Instant.now());

                var authentication = SecurityContextHolder.getContext().getAuthentication();
                var createdBy = authentication == null ? "Can't find current user" : authentication.getName();
                this.strictInsertFill(metaObject, "createdBy", String.class, createdBy);

            }

            @Override
            public void updateFill(MetaObject metaObject) {
                this.strictInsertFill(metaObject, "lastModifiedDate", Instant.class, Instant.now());

                var authentication = SecurityContextHolder.getContext().getAuthentication();
                var lastModifiedBy = authentication == null ? "Can't find current user" : authentication.getName();
                this.strictInsertFill(metaObject, "lastModifiedBy", String.class, lastModifiedBy);
            }
        };
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
