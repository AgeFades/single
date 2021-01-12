package com.agefades.single;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SingleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SingleApplication.class, args);
    }

}
