package com.agefades.single.common.config;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Jackson 配置类
 *
 * @author DuChao
 * @date 2021/1/12 4:04 下午
 */
@Configuration
@ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
public class JacksonConfig {

    @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
    private String pattern;

    @Bean
    public LocalDateTimeSerializer localDateTimeSerializer() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(pattern));
    }

    @Bean
    public LocalDateTimeDeserializer localDateTimeDeserializer() {
        return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(pattern));
    }


    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer(){
        return builder -> {
            // 时区
            builder.locale(Locale.CHINA);
            builder.timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
            // 时间格式化
            builder.simpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
            builder.serializerByType(LocalDateTime.class, localDateTimeSerializer());
            builder.deserializerByType(LocalDateTime.class, localDateTimeDeserializer());
            // 返回页面过滤空字段
            builder.serializationInclusion(JsonInclude.Include.NON_EMPTY);
        };
    }

}