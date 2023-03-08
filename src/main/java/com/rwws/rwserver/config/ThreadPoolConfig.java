package com.rwws.rwserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolConfig {

    @Value("${thread.core-pool-size}")
    private Integer corePoolSize;

    @Value("${thread.queue-capacity}")
    private Integer queueCapacity;

    @Bean(name = "logThreadPool")
    public ThreadPoolTaskExecutor logThreadPoolExecutor() {
        if (null == corePoolSize)
            corePoolSize = Runtime.getRuntime().availableProcessors();
        if (null == queueCapacity)
            queueCapacity = 1000;
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //线程初始化
        taskExecutor.initialize();
        // 设置核心线程数
        taskExecutor.setCorePoolSize(corePoolSize);
        // 设置最大线程数
        taskExecutor.setMaxPoolSize(corePoolSize * 2);
        // 设置队列容量
        taskExecutor.setQueueCapacity(queueCapacity);
        // 设置线程活跃时间（秒）
        taskExecutor.setKeepAliveSeconds(60);
        // 设置默认线程名称
        taskExecutor.setThreadNamePrefix("rw-log-thread-");
        // 设置拒绝策略
        taskExecutor.setRejectedExecutionHandler(new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);

        return taskExecutor;
    }

    @Bean(name = "mailThreadPool")
    public ThreadPoolTaskExecutor mailThreadPoolExecutor() {
        if (null == corePoolSize)
            corePoolSize = Runtime.getRuntime().availableProcessors();
        if (null == queueCapacity)
            queueCapacity = 1000;
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //线程初始化
        taskExecutor.initialize();
        // 设置核心线程数
        taskExecutor.setCorePoolSize(corePoolSize);
        // 设置最大线程数
        taskExecutor.setMaxPoolSize(corePoolSize * 2);
        // 设置队列容量
        taskExecutor.setQueueCapacity(queueCapacity);
        // 设置线程活跃时间（秒）
        taskExecutor.setKeepAliveSeconds(60);
        // 设置默认线程名称
        taskExecutor.setThreadNamePrefix("rw-mail-thread-");
        // 设置拒绝策略
        taskExecutor.setRejectedExecutionHandler(new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);

        return taskExecutor;
    }
}
