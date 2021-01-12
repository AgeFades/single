package com.agefades.single.aspect;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.agefades.single.util.AspectUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 接口入参、出参、耗时日志切面
 *
 * @author DuChao
 * @date 2020/7/27 3:05 下午
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    private final static int MAX_STR_LENGTH = 500;

    /**
     * 切入点
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping) ||" +
            "@annotation(com.agefades.single.annotation.DoLog)")
    public void log() {
    }

    /**
     * 在所有 Controller 方法作增强方法
     */
    @Around("log()")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        // 调用目标方法，方法计时，单位毫秒
        TimeInterval timer = DateUtil.timer();
        Object object = pjp.proceed();
        doLog(pjp, null, object, timer.interval());
        return object;
    }

    @AfterThrowing(pointcut = "log()", throwing = "e")
    public void logThrowing(JoinPoint joinPoint, Throwable e) {
        doLog((ProceedingJoinPoint) joinPoint, e.getMessage(), null, null);
    }

    private void doLog(ProceedingJoinPoint pjp, String message, Object result, Long time) {
        // 获取类名、方法名
        String targetName = pjp.getSignature().getDeclaringTypeName();
        String className = targetName.substring(targetName.lastIndexOf(".") + 1);
        String methodName = pjp.getSignature().getName();

        log.info("【请求参数】:" + className + ":" + methodName + ": " + AspectUtil.getParams(pjp));
        if (StrUtil.isNotBlank(message)) {
            log.info("【异常信息】:" + className + ":" + methodName + ": " + message);
        } else {
            String resultStr = JSONUtil.toJsonStr(result);
            if (StrUtil.isNotBlank(resultStr) && resultStr.length() > MAX_STR_LENGTH) {
                resultStr = resultStr.substring(0, MAX_STR_LENGTH) + "......";
            }
            log.info("【响应结果】:" + className + ":" + methodName + ": " + resultStr);
            log.info("【方法耗时】:" + className + ":" + methodName + ": 耗时: " + time + "毫秒");
        }
    }

}
