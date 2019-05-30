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
package com.springboot.springtransaction.controller;

import com.springboot.springtransaction.domain.Person;
import com.springboot.springtransaction.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo.Weifeng
 * @version: 1.0
 * @create: 2019/4/28 13:59
 */
@RestController
@RequestMapping("/transaction")
public class PersonController {
    @Autowired
    private PersonService personService;

    @RequestMapping("/rollback/{id}/{name}/{age}/{address}")
    public Person rollback(@PathVariable Long id,
                           @PathVariable String name,
                           @PathVariable Integer age,
                           @PathVariable String address){
        Person person = new Person(id, name, age, address);
        return personService.savePersonWithRollBack(person);
    }

    @RequestMapping("/norollback/{id}/{name}/{age}/{address}")
    public Person noRollback(@PathVariable Long id,
                           @PathVariable String name,
                           @PathVariable Integer age,
                           @PathVariable String address){
        Person person = new Person(id, name, age, address);
        return personService.savePersonWithoutRollback(person);
    }
}