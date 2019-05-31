package com.springboot.kafkaaction.producer;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * @author guowf
 * @mail guowf_buaa@163.com
 * @description:
 * @data created in 2019-06-01 07:13
 * 自定义分区类
 */
public class CustomerPartition implements Partitioner {

    private Map configMap = null;

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        // 获取配置中的配置信息
        String keyValue = (String) configMap.getOrDefault(ProducerConfig.ACKS_CONFIG, "1");
        System.out.println("======" + keyValue + "======");
        return 0;
    }

    @Override
    public void close() {

    }

    /**
     * 可以读取配置文件， 来获取某些配置数据
     * */
    @Override
    public void configure(Map<String, ?> map) {
        configMap = map;
    }
}
