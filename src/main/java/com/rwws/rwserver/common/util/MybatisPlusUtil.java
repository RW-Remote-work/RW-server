package com.rwws.rwserver.common.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.domain.Pageable;

public class MybatisPlusUtil {
    public static <T> IPage<T> toPage(Pageable pageable) {
        return Page.of(pageable.getPageNumber(), pageable.getPageSize());
    }
}
