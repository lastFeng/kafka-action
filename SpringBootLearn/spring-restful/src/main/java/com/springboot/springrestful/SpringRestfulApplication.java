package com.springboot.springrestful;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.springboot.springrestful.dao")
public class SpringRestfulApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestfulApplication.class, args);
    }

}
