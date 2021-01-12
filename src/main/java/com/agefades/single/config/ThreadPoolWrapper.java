package com.agefades.single.config;

import com.agefades.single.util.LogUtil;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * 项目线程池包装类
 *  主要实现: 线程 与 异步子线程共享同一个 traceId
 *
 * @author DuChao
 * @date 2020/8/5 2:53 下午
 */
public class ThreadPoolWrapper extends ThreadPoolTaskExecutor {

    @Override
    public void execute(Runnable task) {
        super.execute(LogUtil.wrap(task, LogUtil.getTraceId()));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(LogUtil.wrap(task, LogUtil.getTraceId()));
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return super.submit(LogUtil.wrap(task, LogUtil.getTraceId()));
    }

}
