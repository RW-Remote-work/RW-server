package com.rwws.rwserver.module.support.core;

import com.rwws.rwserver.module.support.domain.OperateLogEntity;
import lombok.Builder;
import lombok.Data;

import java.util.function.Function;

/**
 * 配置
 *
 */
@Data
@Builder
public class OperateLogConfig {

    /**
     * 操作日志存储方法
     */
    private Function<OperateLogEntity, Boolean> saveFunction;

    /**
     * 核心线程数
     */
    private Integer corePoolSize;

    /**
     * 队列大小
     */
    private Integer queueCapacity;


}
