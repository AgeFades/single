package com.agefades.single.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.agefades.single.enums.BizCodeEnum;

import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 业务断言工具类
 *
 * @author DuChao
 * @date 2021/1/12 3:46 下午
 */
public class Assert {

    /**
     * 断言是否为真，如果为 {@code false} 抛出业务响应异常
     * @param expression 布尔值 or 布尔值表达式
     * @param code 业务响应异常Code
     * @param msg 业务响应异常Msg
     */
    public static void isTrue(boolean expression, String code, String msg) {
        if (!expression) {
            ExceptionUtil.throwBizException(code, msg);
        }
    }

    /**
     * 断言是否为真，如果为 {@code false} 抛出业务响应异常
     *
     * @param expression 布尔值 or 布尔值表达式
     * @param codeEnum 业务响应异常枚举
     */
    public static void isTrue(boolean expression, BizCodeEnum codeEnum) {
        isTrue(expression, codeEnum.getCode(), codeEnum.getMsg());
    }

    /**
     * 断言是否为真，如果为 {@code true} 则消费 consumer
     *
     * @param expression 布尔值 or 布尔值表达式
     * @param consumer 消费函数
     */
    public static void isTrue(boolean expression, Consumer<?> consumer) {
        if(expression && consumer != null){
            consumer.accept(null);
        }
    }

    /**
     * 断言是否为假，如果为 {@code true} 抛出业务响应异常
     * @param expression 布尔值 or 布尔值表达式
     * @param code 业务响应异常Code
     * @param msg 业务响应异常Msg
     */
    public static void isFalse(boolean expression, String code, String msg) {
        if (expression) {
            ExceptionUtil.throwBizException(code, msg);
        }
    }

    /**
     * 断言是否为假，如果为 {@code true} 抛出业务响应异常
     *
     * @param expression 布尔值 or 布尔值表达式
     * @param codeEnum 业务响应异常枚举
     */
    public static void isFalse(boolean expression, BizCodeEnum codeEnum) {
        isFalse(expression, codeEnum.getCode(), codeEnum.getMsg());
    }

    /**
     * 断言是否为假，如果为 {@code false} 则消费 consumer
     *
     * @param expression 布尔值 or 布尔值表达式
     * @param consumer 消费函数
     */
    public static void isFalse(boolean expression, Consumer<?> consumer) {
        if(!expression && consumer != null){
            consumer.accept(null);
        }
    }

    /**
     * 断言对象是否为{@code null} ，如果不为{@code null} 抛出业务响应异常
     *
     * @param object 被检查的对象
     * @param code 业务响应异常Code
     * @param msg 业务响应异常Msg
     */
    public static void isNull(Object object, String code, String msg) {
        if (object != null) {
            ExceptionUtil.throwBizException(code, msg);
        }
    }

    /**
     * 断言对象是否为{@code null} ，如果不为{@code null} 抛出业务响应异常
     *
     * @param object 被检查的对象
     * @param codeEnum 业务响应异常枚举
     */
    public static void isNull(Object object, BizCodeEnum codeEnum) {
        isNull(object, codeEnum.getCode(), codeEnum.getMsg());
    }

    /**
     * 断言对象是否为{@code null} ，如果为{@code null} 则消费 consumer
     *
     * @param object 被检查的对象
     * @param consumer 消费函数
     */
    public static void isNull(Object object, Consumer<?> consumer) {
        if(object == null && consumer != null){
            consumer.accept(null);
        }
    }

    /**
     * 断言对象是否不为{@code null} ，如果为{@code null} 抛出业务响应异常
     *
     * @param object 被检查对象泛型类型
     * @param code 业务响应异常Code
     * @param msg 业务响应异常Msg
     * @param <T> 被检查对象泛型类型
     * @return 被检查后的对象
     */
    public static <T> T notNull(T object, String code, String msg) {
        if (object == null) {
            ExceptionUtil.throwBizException(code, msg);
        }
        return object;
    }

    /**
     * 断言对象是否不为{@code null} ，如果为{@code null} 抛出业务响应异常
     *
     * @param object 被检查对象泛型类型
     * @param codeEnum 业务响应异常枚举
     * @param <T> 被检查对象泛型类型
     * @return 被检查后的对象
     */
    public static <T> T notNull(T object, BizCodeEnum codeEnum) {
        return notNull(object, codeEnum.getCode(), codeEnum.getMsg());
    }

    /**
     * 断言对象是否为{@code null} ，如果不为{@code null} 则消费 consumer
     *
     * @param object 被检查的对象
     * @param consumer 消费函数
     */
    public static void notNull(Object object, Consumer<?> consumer) {
        if(object != null && consumer != null){
            consumer.accept(null);
        }
    }

    /**
     * 检查给定字符串是否为空，为空抛出业务响应异常
     *
     * @param text 被检查字符串
     * @param code 业务响应异常Code
     * @param msg 业务响应异常Msg
     * @param <T> 字符串类型
     * @return 非空字符串
     */
    public static <T extends CharSequence> T notEmpty(T text, String code, String msg) {
        if (StrUtil.isEmpty(text)) {
            ExceptionUtil.throwBizException(code, msg);
        }
        return text;
    }

    /**
     * 检查给定字符串是否为空，为空抛出业务响应异常
     *
     * @param text 被检查字符串
     * @param codeEnum 业务响应异常枚举
     * @param <T> 字符串类型
     * @return 非空字符串
     */
    public static <T extends CharSequence> T notEmpty(T text, BizCodeEnum codeEnum) {
        return notEmpty(text, codeEnum.getCode(), codeEnum.getMsg());
    }

    /**
     * 检查给定字符串是否为空，如果不为空 则消费 consumer
     *
     * @param text 被检查的对象
     * @param consumer 消费函数
     */
    public static <T extends CharSequence> T notEmpty(T text, Consumer<?> consumer) {
        if(StrUtil.isNotEmpty(text) && consumer != null){
            consumer.accept(null);
        }
        return text;
    }

    /**
     * 检查给定字符串是否为空白（null、空串或只包含空白符），为空抛出业务响应异常
     *
     * @param text 被检查字符串
     * @param code 业务响应异常Code
     * @param msg 业务响应异常Msg
     * @param <T> 字符串类型
     * @return 非空字符串
     */
    public static <T extends CharSequence> T notBlank(T text, String code, String msg) {
        if (StrUtil.isBlank(text)) {
            ExceptionUtil.throwBizException(code, msg);
        }
        return text;
    }

    /**
     * 检查给定字符串是否为空白（null、空串或只包含空白符），为空抛出业务响应异常
     *
     * @param text 被检查字符串
     * @param codeEnum 业务响应异常枚举
     * @param <T> 字符串类型
     * @return 非空字符串
     */
    public static <T extends CharSequence> T notBlank(T text, BizCodeEnum codeEnum) {
        return notBlank(text, codeEnum.getCode(), codeEnum.getMsg());
    }

    /**
     * 检查给定字符串是否为空白（null、空串或只包含空白符），不为空则消费 consumer
     *
     * @param text 被检查的对象
     * @param consumer 消费函数
     */
    public static <T extends CharSequence> T notBlank(T text, Consumer<?> consumer) {
        if(StrUtil.isBlank(text) && consumer != null){
            consumer.accept(null);
        }
        return text;
    }

    /**
     * 断言给定字符串是否不被另一个字符串包含（即是否为子串）, false 抛出业务响应异常
     *
     * @param textToSearch 被搜索的字符串
     * @param substring 被检查的子串
     * @param code 业务响应异常Code
     * @param msg 业务响应异常Msg
     * @return 被检查的子串
     */
    public static String notContain(String textToSearch, String substring, String code, String msg)  {
        if (StrUtil.isNotEmpty(textToSearch) && StrUtil.isNotEmpty(substring) && textToSearch.contains(substring)) {
            ExceptionUtil.throwBizException(code, msg);
        }
        return substring;
    }

    /**
     * 断言给定字符串是否不被另一个字符串包含（即是否为子串）, false 抛出业务响应异常
     *
     * @param textToSearch 被搜索的字符串
     * @param substring 被检查的子串
     * @param codeEnum 业务响应异常枚举
     * @return 被检查的子串
     */
    public static String notContain(String textToSearch, String substring, BizCodeEnum codeEnum)  {
        return notContain(textToSearch, substring, codeEnum.getCode(), codeEnum.getMsg());
    }

    /**
     * 断言给定数组是否包含元素，数组必须不为 {@code null} 且至少包含一个元素, false 抛出业务响应异常
     *
     * @param array 被检查的数组
     * @return 被检查的数组
     */
    public static Object[] notEmpty(Object[] array, String code, String msg)  {
        if (ArrayUtil.isEmpty(array)) {
            ExceptionUtil.throwBizException(code, msg);
        }
        return array;
    }

    /**
     * 断言给定数组是否包含元素，数组必须不为 {@code null} 且至少包含一个元素, false 抛出业务响应异常
     *
     * @param array 被检查的数组
     * @param codeEnum 业务响应异常枚举
     * @return 被检查的数组
     */
    public static Object[] notEmpty(Object[] array, BizCodeEnum codeEnum)  {
        return notEmpty(array, codeEnum.getCode(), codeEnum.getMsg());
    }

    /**
     * 断言给定数组是否不包含{@code null}元素，如果数组为空或 {@code null}将被认为不包含, false 抛出业务响应异常
     *
     * @param <T> 数组元素类型
     * @param array 被检查的数组
     * @param code 业务响应异常Code
     * @param msg 业务响应异常Msg
     * @return 被检查的数组
     */
    public static <T> T[] noNullElements(T[] array, String code, String msg)  {
        if (ArrayUtil.hasNull(array)) {
            ExceptionUtil.throwBizException(code, msg);
        }
        return array;
    }

    /**
     * 断言给定数组是否不包含{@code null}元素，如果数组为空或 {@code null}将被认为不包含, false 抛出业务响应异常
     *
     * @param <T> 数组元素类型
     * @param array 被检查的数组
     * @param codeEnum 业务响应异常枚举
     * @return 被检查的数组
     */
    public static <T> T[] noNullElements(T[] array, BizCodeEnum codeEnum)  {
        return noNullElements(array, codeEnum.getCode(), codeEnum.getMsg());
    }

    /**
     * 断言给定集合非空, false 抛出业务响应异常
     *
     * @param <T> 集合元素类型
     * @param collection 被检查的集合
     * @param code 业务响应异常Code
     * @param msg 业务响应异常Msg
     * @return 被检查集合
     */
    public static <T> Collection<T> notEmpty(Collection<T> collection, String code, String msg)  {
        if (CollUtil.isEmpty(collection)) {
            ExceptionUtil.throwBizException(code, msg);
        }
        return collection;
    }

    /**
     * 断言给定集合非空, false 抛出业务响应异常
     *
     * @param <T> 集合元素类型
     * @param collection 被检查的集合
     * @param codeEnum 业务响应异常枚举
     * @return 非空集合
     */
    public static <T> Collection<T> notEmpty(Collection<T> collection, BizCodeEnum codeEnum)  {
        return notEmpty(collection, codeEnum.getCode(), codeEnum.getMsg());
    }

    /**
     * 断言给定Map非空, false 抛出业务响应异常
     *
     * @param <K> Key类型
     * @param <V> Value类型
     *
     * @param map 被检查的Map
     * @param code 业务响应异常Code
     * @param msg 业务响应异常Msg
     * @return 被检查的Map
     */
    public static <K, V> Map<K, V> notEmpty(Map<K, V> map, String code, String msg)  {
        if (CollUtil.isEmpty(map)) {
            ExceptionUtil.throwBizException(code, msg);
        }
        return map;
    }

    /**
     * 断言给定Map非空, false 抛出业务响应异常
     *
     * @param <K> Key类型
     * @param <V> Value类型
     *
     * @param map 被检查的Map
     * @param codeEnum 业务响应异常枚举
     * @return 被检查的Map
     */
    public static <K, V> Map<K, V> notEmpty(Map<K, V> map, BizCodeEnum codeEnum)  {
       return notEmpty(map, codeEnum.getCode(), codeEnum.getMsg());
    }

    /**
     * 断言给定对象是否是给定类的实例, false 抛出业务响应异常
     *
     * @param <T> 被检查对象泛型类型
     * @param type 被检查对象匹配的类型
     * @param obj 被检查对象
     * @param code 业务响应异常Code
     * @param msg 业务响应异常Msg
     * @return 被检查的对象
     */
    public static <T> T isInstanceOf(Class<?> type, T obj, String code, String msg) {
        notNull(type, BizCodeEnum.ILLEGAL_ARGUMENT.getCode(), "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            ExceptionUtil.throwBizException(code, msg);
        }
        return obj;
    }

    /**
     * 断言给定对象是否是给定类的实例, false 抛出业务响应异常
     *
     * @param <T> 被检查对象泛型类型
     * @param type 被检查对象匹配的类型
     * @param obj 被检查对象
     * @param codeEnum 业务响应异常枚举
     * @return 被检查对象
     */
    public static <T> T isInstanceOf(Class<?> type, T obj, BizCodeEnum codeEnum)  {
        return isInstanceOf(type, obj, codeEnum.getCode(), codeEnum.getMsg());
    }

    /**
     * 断言子类是否继承父类, false 抛出业务响应异常
     *
     * @param superType 需要检查的父类或接口
     * @param subType 需要检查的子类
     * @param code 业务响应异常Code
     * @param msg 业务响应异常Msg
     */
    public static void isAssignable(Class<?> superType, Class<?> subType, String code, String msg)  {
        notNull(superType, BizCodeEnum.ILLEGAL_ARGUMENT.getCode(), "Type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            ExceptionUtil.throwBizException(code, msg);
        }
    }

    /**
     * 断言子类是否继承父类, false 抛出业务响应异常
     *
     * @param superType 需要检查的父类或接口
     * @param subType 需要检查的子类
     * @param codeEnum 业务响应异常枚举
     */
    public static void isAssignable(Class<?> superType, Class<?> subType, BizCodeEnum codeEnum)  {
       isAssignable(superType, subType, codeEnum.getCode(), codeEnum.getMsg());
    }

    /**
     * 检查boolean表达式, false 抛出业务响应异常
     *
     * @param expression boolean 表达式
     * @param codeEnum 业务响应异常枚举
     */
    public static void state(boolean expression, BizCodeEnum codeEnum) throws IllegalStateException {
        state(expression, codeEnum.getCode(), codeEnum.getMsg());
    }

    /**
     * 检查boolean表达式, false 抛出业务响应异常
     *
     * @param expression boolean 表达式
     * @param code 业务响应异常Code
     * @param msg 业务响应异常Msg
     */
    public static void state(boolean expression, String code, String msg) throws IllegalStateException {
        if (!expression) {
            ExceptionUtil.throwBizException(code, msg);
        }
    }

    /**
     * 检查下标（数组、集合、字符串）是否符合要求，下标必须满足：0 < index <= size, false 抛出业务响应异常
     *
     * @param index 下标
     * @param size 长度
     * @param code 业务响应异常Code
     * @param msg 业务响应异常Msg
     * @return 检查后的下标
     */
    public static int checkIndex(int index, int size, String code, String msg) {
        if (index < 0 || index >= size) {
            ExceptionUtil.throwBizException(code, msg);
        }
        return index;
    }

    /**
     * 检查下标（数组、集合、字符串）是否符合要求，下标必须满足：0 < index <= size, false 抛出业务响应异常
     *
     * @param index 下标
     * @param size 长度
     * @param codeEnum 业务响应异常枚举
     * @return 检查后的下标
     */
    public static int checkIndex(int index, int size, BizCodeEnum codeEnum) {
        return checkIndex(index, size, codeEnum.getCode(), codeEnum.getMsg());
    }

    /**
     * 检查值是否在指定范围内, false 抛出业务响应异常
     *
     * @param value 值
     * @param min 最小值（包含）
     * @param max 最大值（包含）
     * @param code 业务响应异常Code
     * @param msg 业务响应异常Msg
     * @return 检查后的长度值
     */
    public static int checkBetween(int value, int min, int max, String code, String msg) {
        if (value < min || value > max) {
            ExceptionUtil.throwBizException(code, msg);
        }
        return value;
    }

    /**
     * 检查值是否在指定范围内, false 抛出业务响应异常
     *
     * @param value 值
     * @param min 最小值（包含）
     * @param max 最大值（包含）
     * @param codeEnum 业务响应异常枚举
     * @return 检查后的长度值
     */
    public static int checkBetween(int value, int min, int max, BizCodeEnum codeEnum) {
        return checkBetween(value, min, max, codeEnum.getCode(), codeEnum.getMsg());
    }

    /**
     * 检查值是否在指定范围内, false 抛出业务响应异常
     *
     * @param value 值
     * @param min 最小值（包含）
     * @param max 最大值（包含）
     * @param code 业务响应异常Code
     * @param msg 业务响应异常Msg
     * @return 检查后的长度值
     */
    public static long checkBetween(long value, long min, long max, String code, String msg) {
        if (value < min || value > max) {
            ExceptionUtil.throwBizException(code, msg);
        }
        return value;
    }

    /**
     * 检查值是否在指定范围内, false 抛出业务响应异常
     *
     * @param value 值
     * @param min 最小值（包含）
     * @param max 最大值（包含）
     * @param codeEnum 业务响应异常枚举
     * @return 检查后的长度值
     */
    public static long checkBetween(long value, long min, long max, BizCodeEnum codeEnum) {
        return checkBetween(value, min, max, codeEnum.getCode(), codeEnum.getMsg());
    }

    /**
     * 检查值是否在指定范围内, false 抛出业务响应异常
     *
     * @param value 值
     * @param min 最小值（包含）
     * @param max 最大值（包含）
     * @param code 业务响应异常Code
     * @param msg 业务响应异常Msg
     * @return 检查后的长度值
     */
    public static double checkBetween(double value, double min, double max, String code, String msg) {
        if (value < min || value > max) {
            ExceptionUtil.throwBizException(code, msg);
        }
        return value;
    }

    /**
     * 检查值是否在指定范围内, false 抛出业务响应异常
     *
     * @param value 值
     * @param min 最小值（包含）
     * @param max 最大值（包含）
     * @param codeEnum 业务响应异常枚举
     * @return 检查后的长度值
     */
    public static double checkBetween(double value, double min, double max, BizCodeEnum codeEnum) {
        return checkBetween(value, min, max, codeEnum.getCode(), codeEnum.getMsg());
    }

    /**
     * 检查值是否在指定范围内, false 抛出业务响应异常
     *
     * @param value 值
     * @param min 最小值（包含）
     * @param max 最大值（包含）
     * @param code 业务响应异常Code
     * @param msg 业务响应异常Msg
     * @return 检查后的长度值
     */
    public static Number checkBetween(Number value, Number min, Number max, String code, String msg) {
        notNull(value, BizCodeEnum.ILLEGAL_ARGUMENT.getCode(), "value 不能为空");
        notNull(min, BizCodeEnum.ILLEGAL_ARGUMENT.getCode(), "min 不能为空");
        notNull(max, BizCodeEnum.ILLEGAL_ARGUMENT.getCode(), "max 不能为空");
        double valueDouble = value.doubleValue();
        double minDouble = min.doubleValue();
        double maxDouble = max.doubleValue();
        if (valueDouble < minDouble || valueDouble > maxDouble) {
            ExceptionUtil.throwBizException(code, msg);
        }
        return value;
    }

    /**
     * 检查值是否在指定范围内, false 抛出业务响应异常
     *
     * @param value 值
     * @param min 最小值（包含）
     * @param max 最大值（包含）
     * @param codeEnum 业务响应异常枚举
     * @return 检查后的长度值
     */
    public static Number checkBetween(Number value, Number min, Number max, BizCodeEnum codeEnum) {
        return checkBetween(value, min, max, codeEnum.getCode(), codeEnum.getMsg());
    }

}
