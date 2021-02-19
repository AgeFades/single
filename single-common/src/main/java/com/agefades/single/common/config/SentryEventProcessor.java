package com.agefades.single.common.config;

import com.agefades.single.common.util.LogUtil;
import io.sentry.SentryEvent;
import io.sentry.SentryOptions;
import org.springframework.stereotype.Component;

@Component
public class SentryEventProcessor implements SentryOptions.BeforeSendCallback {

    @Override
    public SentryEvent execute(SentryEvent event, Object hint) {
        // 异常事件注册 TraceId 标签键值对
        event.setTag("traceId", LogUtil.getTraceId());
        return event;
    }

}
