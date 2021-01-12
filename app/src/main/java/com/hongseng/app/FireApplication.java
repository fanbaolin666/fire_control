package com.hongseng.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import utils.SpringContextHolder;

/**
 * @author Administrator
 */
@SpringBootApplication
public class FireApplication {

    public static void main(String[] args) {
        SpringApplication.run(FireApplication.class, args);
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

}
