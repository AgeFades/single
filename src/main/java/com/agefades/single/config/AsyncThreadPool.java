package com.agefades.single.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步线程池
 *
 * @author DuChao
 * @date 2021/1/12 4:03 下午
 */
@Slf4j
@Configuration
@EnableAsync
public class AsyncThreadPool implements AsyncConfigurer {

    @Bean
    @Override
    public Executor getAsyncExecutor() {

        // JVM 可用CPU核数
        int processors = Runtime.getRuntime().availableProcessors();

        ThreadPoolTaskExecutor executor = new ThreadPoolWrapper();
        // 核心线程池大小
        executor.setCorePoolSize(processors);
        // 最大线程数
        executor.setMaxPoolSize(processors * 2);
        // 队列容量
        executor.setQueueCapacity(processors * 2);
        // 多余线程最大空闲存活时间
        executor.setKeepAliveSeconds(300);
        // 线程名字前缀
        executor.setThreadNamePrefix("Async-");

        // setRejectedExecutionHandler：当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    /**
     * 异步任务中异常处理
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (arg0, arg1, arg2) -> {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            arg0.printStackTrace(pw);

            log.error("\n异步任务执行失败, 异常原因 : \n{}, 异常方法 : {}", sw.toString(), arg1.getName());
        };
    }

}
