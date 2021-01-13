package com.agefades.single.admin.biz.sys.service;

import com.agefades.single.admin.biz.sys.entity.SysLog;
import com.agefades.single.admin.biz.sys.req.SysLogQueryReq;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统操作日志
 *
 * @author DuChao
 * @date 2020-07-24 13:54:08
 */
public interface SysLogService extends IService<SysLog> {

    /**
     * 切面存储操作日志
     *
     * @param point    : 切面point
     * @param errorMsg : 异常Msg
     * @param l        : 耗时时间
     * @param request  : 请求
     * @param ip       : ip
     * @param username : 当前登录用户名
     */
    @Async
    void saveLog(ProceedingJoinPoint point, String errorMsg, long l, HttpServletRequest request, String ip, String username);

    /**
     * 多条件分页查询
     *
     * @param req {@link SysLogQueryReq}
     * @return 操作日志分页数据
     */
    Page<SysLog> conditionPage(SysLogQueryReq req);

}
