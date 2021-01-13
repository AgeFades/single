package com.agefades.single.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan({"com.agefades.single.admin.biz.*.mapper"})
@ComponentScan(basePackages = { "com.agefades.single.common.*", "com.agefades.single.admin.*" })
public class SingleAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SingleAdminApplication.class, args);
    }

}
