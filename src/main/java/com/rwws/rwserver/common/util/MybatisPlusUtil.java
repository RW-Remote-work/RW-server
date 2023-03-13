package com.rwws.rwserver.common.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.domain.Pageable;

import static org.springframework.data.domain.Sort.Direction.ASC;

public class MybatisPlusUtil {
    public static <T> IPage<T> toPage(Pageable pageable) {
        return new CustomPage<>(pageable);
    }

    private static class CustomPage<T> extends Page<T> {
        private CustomPage(Pageable pageable) {
            this.current = pageable.getPageNumber();
            this.size = pageable.getPageSize();
            this.orders = pageable.getSort().stream()
                    .map(it -> it.getDirection() == ASC ? OrderItem.asc(it.getProperty()) : OrderItem.desc(it.getProperty()))
                    .toList();

        }

        @Override
        public long offset() {
            return Math.max(current * getSize(), 0L);
        }
    }
}
