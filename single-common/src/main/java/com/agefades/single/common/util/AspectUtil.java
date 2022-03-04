package com.agefades.single.common.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 切面工具类
 *
 * @author DuChao
 * @date 2020/7/27 2:38 下午
 */
public class AspectUtil {

    public static String getParams(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (ArrayUtil.isEmpty(args)) {
            return null;
        }
        Map<String, Object> paramsMap = getParamsMap(joinPoint, args);
        if (CollUtil.isEmpty(paramsMap)) {
            // 获取参数
            StringBuilder paramsBuilder = new StringBuilder("{ ");
            Arrays.stream(args).forEach(arg -> {
                if (arg != null) {
                    if (arg instanceof MultipartFile) {
                        arg = ((MultipartFile) arg).getOriginalFilename();
                    } else if (!ObjectUtil.isBasicType(arg)) {
                        arg = JSONUtil.toJsonStr(arg);
                    }
                    paramsBuilder.append(arg).append(" ");
                }
            });
            return paramsBuilder + "} ";
        } else {
            return JSONUtil.toJsonStr(paramsMap);
        }
    }

    /**
     * 获取参数列表Map
     *
     * @param joinPoint : 切面对象
     * @param args      : 参数值数组
     * @return 参数列表Map JSONStr
     */
    private static Map<String, Object> getParamsMap(JoinPoint joinPoint, Object[] args) {
        Map<String, Object> paramMap = new HashMap<>();
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            String[] parameterNames = methodSignature.getParameterNames();
            assert parameterNames != null;
            for (int i = 0; i < parameterNames.length; i++) {
                paramMap.put(parameterNames[i], args[i]);
            }
        }
        return paramMap;
    }
}
