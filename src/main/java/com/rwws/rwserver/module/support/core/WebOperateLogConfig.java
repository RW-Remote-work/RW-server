package com.rwws.rwserver.module.support.core;

import com.rwws.rwserver.module.support.domain.OperateLog;
import lombok.Builder;
import lombok.Data;

import java.util.function.Function;

/**
 * 配置
 *
 */
@Data
@Builder
public class WebOperateLogConfig {

    /**
     * 操作日志存储方法
     */
    private Function<OperateLog, Boolean> saveFunction;
}
