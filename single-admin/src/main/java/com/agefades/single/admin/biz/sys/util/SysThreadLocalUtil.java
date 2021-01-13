package com.agefades.single.admin.biz.sys.util;

import com.agefades.single.common.util.RedisUtil;
import com.agefades.single.common.util.SpringUtil;
import com.agefades.single.common.util.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 系统线程上下文工具类
 *
 * @author DuChao
 * @date 2020/9/2 11:07 上午
 */
@Slf4j
public class SysThreadLocalUtil extends ThreadLocalUtil {

    private static final RedisUtil redisUtil = SpringUtil.getBean(RedisUtil.class);

    /**
     * 当前用户存储
     */
    private static final String CUR_SYS_USER = "cur_sys_user";

//    /**
//     * 系统线程上下文设值当前用户信息
//     *
//     * @param dto {@link SysUserDTO}
//     */
//    public static void setCurUser(SysUserDTO dto) {
//        log.info("系统线程上下文设值当前用户: {}", JSONUtil.toJsonStr(dto));
//        set(CUR_SYS_USER, dto);
//    }
//
//    /**
//     * 系统线程上下文获取当前用户信息
//     *
//     * @return {@link SysUserDTO}
//     * @throws RuntimeException 取不到用户或用户id会抛出异常
//     */
//    public static SysUserDTO getCurUser() throws RuntimeException {
//        SysUserDTO dto = get(CUR_SYS_USER, SysUserDTO.class);
//        log.info("系统线程上下文获取当前用户: {}", JSONUtil.toJsonStr(dto));
//        VospAssert.isTrue(ObjectUtil.isNotNull(dto), CommonResultCodeEnum.TOKEN_EXPIRED_ERROR);
//        return dto;
//    }

//    /**
//     * 判断当前用户是否超级管理员
//     *
//     * @return true=是, false=否
//     */
//    public static Boolean isAdmin() {
//        return getCurUser().getIsAdmin() == YesNoEnum.Y.getCode();
//    }


    /**
     * 获取当前用户名 | 不抛出异常，线程上下文用户为空时返回 "匿名用户"
     *
     * @return 当前用户名
     */
    public static String getCurUserName() {
//        SysUserDTO dto = get(CUR_SYS_USER, SysUserDTO.class);
//        log.info("系统线程上下文获取当前用户: {}", JSONUtil.toJsonStr(dto));
//        if (dto == null) {
            return "匿名用户";
//        }
//        return dto.getUsername();
    }

//    /**
//     * 系统线程上下文获取当前用户id
//     *
//     * @return 用户id
//     * @throws RuntimeException 取不到用户或用户id会抛出异常
//     */
//    public static String getCurUserId() throws RuntimeException {
//        return getCurUser().getId();
//    }

//    /**
//     * 刷新线程上下文当前用户信息
//     *
//     * @param dto {@link SysUserDTO}
//     */
//    public static void refreshCurUser(SysUserDTO dto) {
//        String hashKey = RedisConstant.SYS_USER_INFO_PREFIX + dto.getId();
//        redisUtil.hPut(hashKey, RedisConstant.SYS_USER_DTO, JSONUtil.toJsonStr(dto));
//    }

}
