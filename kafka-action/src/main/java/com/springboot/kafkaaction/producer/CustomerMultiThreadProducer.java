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

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo.Weifeng
 * @version: 1.0
 * @create: 2019/6/3 10:25
 * 多线程模式
 *  ：KafkaProducer是线程安全的，同时其共享一个实例比每个线程各自实例化一个实例效率要高
 */
public class CustomerMultiThreadProducer implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(CustomerMultiThreadProducer.class);

    private KafkaProducer<String, String> producer = null;

    private ProducerRecord<String, String> record = null;

    /**
     * 构造器
     * @param producer KafkaProducer
     * @param record ProducerRecord
     * */
    public CustomerMultiThreadProducer(KafkaProducer<String, String> producer,  ProducerRecord<String, String> record){
        this.producer = producer;
        this.record = record;
    }

    /**
     * 多线程实现
     * */
    @Override
    public void run() {
        producer.send(record, (recordMetadata, exception) -> {
            if (exception != null){
                logger.error("发送失败");
            }
            else{
                logger.info(recordMetadata.partition() + "-----" + recordMetadata.offset());
            }
        });
    }

    /**
     * 测试
     * */
    public static void main(String[] args) {
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
        interceptors.add("com.springboot.kafkaaction.interceptor.CustomerProducerInterceptor");
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);


        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("demo-pool-%d").build();

        //Common Thread Pool
        ExecutorService pool = new ThreadPoolExecutor(5, 200,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());


        for (int i = 0; i < 10; i++){
            ProducerRecord<String, String> finalRecord = new ProducerRecord<>(String.valueOf(i), String.valueOf(i));
            pool.execute(()-> new CustomerMultiThreadProducer(producer, finalRecord));
        }

        pool.shutdown();//gracefully shutdown
    }
}