package com.agefades.single.biz.sys.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.hutool.json.JSONUtil;
import com.agefades.single.base.PageReq;
import com.agefades.single.biz.sys.entity.SysLog;
import com.agefades.single.biz.sys.mapper.SysLogMapper;
import com.agefades.single.biz.sys.req.SysLogQueryReq;
import com.agefades.single.biz.sys.service.SysLogService;
import com.agefades.single.constants.CommonConstant;
import com.agefades.single.util.AspectUtil;
import com.agefades.single.util.LogUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 系统操作日志
 *
 * @author DuChao
 * @date 2020-07-24 13:54:08
 */
@Slf4j
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Override
    public void saveLog(ProceedingJoinPoint point, String errorMsg, long elapsedTime, HttpServletRequest request, String ip, String username) {
        SysLog sysLog = new SysLog();
        if (StrUtil.isNotBlank(errorMsg)) {
            sysLog.setResultType(CommonConstant.ERROR);
            sysLog.setErrorMsg(StrUtil.sub(errorMsg, 0, 250));
        } else {
            sysLog.setResultType(CommonConstant.SUCCESS);
        }
        sysLog.setTraceId(LogUtil.getTraceId());
        sysLog.setElapsedTime(elapsedTime + "毫秒");

        // 获取类名、方法名、方法注解
        String targetName = point.getSignature().getDeclaringTypeName();
        String className = targetName.substring(targetName.lastIndexOf(".") + 1);
        String methodName = point.getSignature().getName();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        com.agefades.single.annotation.SysLog sysLogAnnotation = method.getAnnotation(com.agefades.single.annotation.SysLog.class);
        sysLog.setClassName(className);
        sysLog.setMethodName(methodName);
        sysLog.setLogDesc(sysLogAnnotation.desc());

        if (ObjectUtil.isNotNull(request)) {
            // 获取请求类型、浏览器、系统、平台
            String methodType = request.getMethod();
            UserAgent userAgent = UserAgentUtil.parse(request.getHeader("User-Agent"));
            if (userAgent != null) {
                String browser = userAgent.getBrowser().getName() + userAgent.getBrowser().getVersion(request.getHeader("User-Agent"));
                String os = userAgent.getOs().getName();
                String platform = userAgent.getPlatform().getName();
                sysLog.setMethodType(methodType);
                sysLog.setBrowser(browser);
                sysLog.setOs(StrUtil.sub(os, 0, 30));
                sysLog.setPlatform(platform);
            }
        }

        // 获取当前用户、IP
        sysLog.setUsername(username);
        sysLog.setIp(ip);

        sysLog.setParams(AspectUtil.getParams(point));
        log.info("SysLog 切面操作日志: {}", JSONUtil.toJsonStr(sysLog));
        save(sysLog);
    }

    @Override
    public Page<SysLog> conditionPage(SysLogQueryReq req) {
        if (req == null) {
            return page(new PageReq<SysLog>().page());
        } else {
            return lambdaQuery()
                    .like(StrUtil.isNotBlank(req.getUsername()), SysLog::getUsername, req.getUsername())
                    .like(StrUtil.isNotBlank(req.getLogDesc()), SysLog::getLogDesc, req.getLogDesc())
                    .eq(StrUtil.isNotBlank(req.getResultType()), SysLog::getResultType, req.getResultType())
                    .eq(StrUtil.isNotBlank(req.getTraceId()), SysLog::getTraceId, req.getTraceId())
                    .ge(ObjectUtil.isNotNull(req.getStartTime()), SysLog::getCreateTime, req.getStartTime())
                    .le(ObjectUtil.isNotNull(req.getEndTime()), SysLog::getCreateTime, req.getEndTime())
                    .page(req.page());
        }
    }

}
