/*
 * Copyright 2001-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.springboot.o2o.redis;

import com.springboot.o2o.dao.RedisDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertNotNull;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo.Weifeng
 * @version: 1.0
 * @create: 2019/5/15 13:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisConnectTest {
    @Autowired
    private RedisDao redisDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testRedisSetAndGet(){
        String key = "hello:test";
        String value = "123";
        redisDao.setKey(key, value);
        String status = redisDao.getValue(key);
        System.out.println(status);
        assertNotNull(status);
        redisTemplate.delete(key);
    }
}