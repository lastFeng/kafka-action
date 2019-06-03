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
package com.springboot.kafkaaction.property;

import com.springboot.kafkaaction.interceptor.CustomerProducerInterceptor;
import com.springboot.kafkaaction.producer.CustomerPartition;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo.Weifeng
 * @version: 1.0
 * @create: 2019/6/3 15:28
 */
public class PropertiesUtil {

    /**
     * 设置生产者的属性
     * */
    public static Properties setProducerProperties(){
        /**
         * Kafka配置信息,除了序列化优化时使用
         * */
        Properties props = new Properties();
        // kafka集群
        props.put("bootstrap.servers", "localhost:9092");
        // 数据同步机制(all==-1)
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        // 重试次数，0则是直接抛弃
        props.put("retries", 0);
        // 批量大小
        props.put("batch.size", 16384);
        // 提交延迟,等待时间到达或者一次性发送批量大小达到上限时发送
        props.put("linger.ms", 1);
        // producer缓存数据大小
        props.put("buffer.memory", 33554432);
        // 设置分区自定义类
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomerPartition.class.getName());
        // key的序列化，对key进行序列化
        props.put("key.serializer", StringSerializer.class.getName());
        // value的序列化， 对value进行序列化
        props.put("value.serializer", StringSerializer.class.getName());

        // 添加拦截器
        List<String> interceptors = new ArrayList<>();
        interceptors.add(CustomerProducerInterceptor.class.getName());
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);

        return props;
    }

    /**
     * 设置消费者的属性
     * */
    public static Properties setConsumerProperties(){
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
        return props;
    }
}