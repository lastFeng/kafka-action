package com.springboot.o2o;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.springboot.o2o.dao")
public class SpringbootO2oApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootO2oApplication.class, args);
    }

}
