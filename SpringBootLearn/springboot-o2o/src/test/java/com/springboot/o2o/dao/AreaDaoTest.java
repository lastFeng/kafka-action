///*
// * Copyright 2001-2017 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package com.springboot.o2o.dao;
//
//import com.springboot.o2o.domain.Area;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * <p> Title: </p>
// *
// * <p> Description: </p>
// *
// * @author: Guo.Weifeng
// * @version: 1.0
// * @create: 2019/5/15 10:43
// */
//@RunWith(SpringRunner.class)
//@MapperScan(value = "com.springboot.o2o.dao")
//@SpringBootTest
//public class AreaDaoTest {
//    @Autowired
//    private AreaDao areaDao;
//
//    @Test
//    public void testQueryArea(){
//        List<Area> list = areaDao.queryArea();
//        list.forEach(item -> System.out.println(item.getAreaName()));
//        assertEquals(4, list.size());
//    }
//}