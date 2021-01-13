package com.agefades.single.common.util;

import cn.hutool.json.JSONUtil;
import com.agefades.single.common.base.Result;
import com.agefades.single.common.enums.CodeEnum;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Response 写出通用工具类
 *
 * @author DuChao
 * @date 2020/7/9 11:12 上午
 */
@Slf4j
public class ResponseUtil {

    /**
     * 往 response 写出 json
     * @param response : 响应流
     * @param codeEnum {@link CodeEnum}
     */
    public static void renderJson(HttpServletResponse response, CodeEnum codeEnum) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.addHeader("Access-Control-Expose-Headers", "Authorization");
            response.setContentType("application/json;charset=UTF-8");

            response.getWriter()
                    .write(JSONUtil.toJsonStr(Result.error(codeEnum)));
        } catch (IOException e) {
            log.error("Response写出JSON异常，", e);
        }
    }

    /**
     * 往 response 写出 json
     * @param response : 响应流
     * @param code : 异常code
     * @param msg : 异常msg
     */
    public static void renderJson(HttpServletResponse response, String code, String msg) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setContentType("application/json;charset=UTF-8");

            response.getWriter()
                    .write(JSONUtil.toJsonStr(Result.error(code, msg)));
        } catch (IOException e) {
            log.error("Response写出JSON异常，", e);
        }
    }

}