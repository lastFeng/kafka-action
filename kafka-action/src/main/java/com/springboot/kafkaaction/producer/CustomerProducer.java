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
package com.springboot.kafkaaction.producer;

import com.springboot.kafkaaction.property.PropertiesUtil;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo.Weifeng
 * @version: 1.0
 * @create: 2019/5/31 17:12
 */
public class CustomerProducer {
    public static void main(String[] args) {

        Properties props = PropertiesUtil.setProducerProperties();

        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 10; i++) {
            // 发送生产数据
//            producer.send(new ProducerRecord<String, String>("first", Integer.toString(i), Integer.toString(i)));

            // 添加回调函数的参数
            producer.send(new ProducerRecord<String, String>("first", Integer.toString(i), Integer.toString(i)),
                    (recordMetadata, exception) -> {
                        if (exception != null){
                            System.out.println("发送失败");
                        }
                        else{
                            System.out.println(recordMetadata.partition() + "-----" + recordMetadata.offset());
                        }
                    });
        }
        producer.close();
    }
}