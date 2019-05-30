package com.springboot.o2o;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@MapperScan(value = "com.springboot.o2o.dao")
@SpringBootTest
public class SpringbootO2oApplicationTests {

    @Test
    public void contextLoads() {
    }

}
