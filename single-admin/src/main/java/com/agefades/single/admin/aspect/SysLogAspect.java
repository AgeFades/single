package com.agefades.single.admin.aspect;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.ObjectUtil;
import com.agefades.single.admin.biz.sys.service.SysLogService;
import com.agefades.single.admin.util.SysThreadLocalUtil;
import com.agefades.single.common.util.IpUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统日志注解处理切面
 *
 * @author DuChao
 * @date 2020/7/24 10:23 上午
 */
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    SysLogService sysLogService;

    private final ThreadLocal<TimeInterval> timer = new ThreadLocal<>();

    /**
     * 切点 SysLog 注解
     */
    @Pointcut("@annotation(com.agefades.single.admin.annotation.SysLog)")
    public void sysLog() {
    }

    /**
     * 环绕通知
     */
    @Around("sysLog()")
    public Object sysLogAround(ProceedingJoinPoint point) throws Throwable {
        // 调用目标方法，方法计时，单位毫秒
        timer.set(DateUtil.timer());
        Object result = point.proceed();
        saveLog(point, null);
        return result;
    }

    @AfterThrowing(pointcut = "sysLog()", throwing = "e")
    public void sysLogThrowing(JoinPoint joinPoint, Throwable e) {
        saveLog((ProceedingJoinPoint) joinPoint, e.getMessage());
    }

    private void saveLog(ProceedingJoinPoint point, String errorMsg) {
        sysLogService.saveLog(point, errorMsg, timer.get().interval(), getReq(), IpUtil.getIpAddr(), SysThreadLocalUtil.getCurUserName());
        timer.remove();
    }

    private HttpServletRequest getReq() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (ObjectUtil.isNotNull(attributes)) {
            return attributes.getRequest();
        }
        return null;
    }

}
