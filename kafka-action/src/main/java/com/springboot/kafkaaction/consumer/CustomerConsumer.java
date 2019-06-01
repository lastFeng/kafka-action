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

import org.apache.kafka.clients.consumer.ConsumerConfig;
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
        Properties props = new Properties();
        // 集群
        props.put("bootstrap.servers", "localhost:9092");
        // 消费者组id
        props.put( "group.id", "test");

        // 设置读取信息的偏移量，默认是latest:最新信息--- earliest:最早数据（数据过时之后，数据偏移量不是从0开始）
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // 是否自动提交--自动提交offset
        props.put( "enable.auto.commit", "true");
        // 提交间隔时间，可能会出现数据不同步---数据读取成功之后，还未保存offset时出现问题，那么下次读取会出现重复消费已消费的数据
        props.put( "auto.commit.interval.ms", "1000");
        // key的序列化
        props.put( "key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // value的序列化
        props.put( "value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

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