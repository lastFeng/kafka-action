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
package com.springboot.springwebservice.service.impl;

import com.springboot.springwebservice.service.UserService;

import javax.jws.WebService;
import java.io.UnsupportedEncodingException;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo.Weifeng
 * @version: 1.0
 * @create: 2019/5/20 14:02
 */

/**
 * 定义webService
 * name为“test”
 * targetNamespace为包名的倒写，如果没写则默认类下的包名
 * endpointInterface：为实现接口的具体位置，精确到接口名
 * */
@WebService(name = "test", targetNamespace = "http://service.springwebservice.springboot.com",
    endpointInterface = "com.springboot.springwebservice.service.UserService")
public class UserServiceImpl implements UserService {

    @Override
    public String getUserName(String id) throws UnsupportedEncodingException {
        return "My name is: Dada";
    }

    @Override
    public String getUser(String id) throws UnsupportedEncodingException {
        return "Hello User";
    }
}