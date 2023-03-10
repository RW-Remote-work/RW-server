package com.rwws.rwserver.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    @Value("${thread.core-pool-size}")
    private Integer corePoolSize;

    @Value("${thread.queue-capacity}")
    private Integer queueCapacity;

    @Override
    public Executor getAsyncExecutor() {
        return this.mailThreadPoolExecutor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }

    private ThreadPoolTaskExecutor mailThreadPoolExecutor() {
        if (null == corePoolSize)
            corePoolSize = Runtime.getRuntime().availableProcessors();
        if (null == queueCapacity)
            queueCapacity = 1000;
        var taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.initialize();
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(corePoolSize * 2);
        taskExecutor.setQueueCapacity(queueCapacity);
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setThreadNamePrefix("rw-pool0-");
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        return taskExecutor;
    }
}
