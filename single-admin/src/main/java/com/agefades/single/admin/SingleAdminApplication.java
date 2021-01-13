package com.agefades.single.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = { "com.agefades.single.common.*", "com.agefades.single.admin.*" })
public class SingleAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SingleAdminApplication.class, args);
    }

}
