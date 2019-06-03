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
package com.springboot.kafkaaction.consumer;

import com.springboot.kafkaaction.property.PropertiesUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo.Weifeng
 * @version: 1.0
 * @create: 2019/5/31 17:22
 */
public class CustomerConsumer {
    public static void main(String[] args) {
        Properties props = PropertiesUtil.setConsumerProperties();

        KafkaConsumer <String, String> consumer = new KafkaConsumer<>(props);
        // 订阅模式，指定订阅的topic
        consumer.subscribe(Arrays.asList( "second"));
        while (true) {
            // 通过poll来获取数据
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String,String> record: records) {
                // 对获取的数据进行处理--存入哪里都行
                System.out.printf(" offset = %d, key = %s, value = %s, partition=%d %n",
                        record.offset(), record.key(), record.value(), record.partition());
            }
        }
    }
}