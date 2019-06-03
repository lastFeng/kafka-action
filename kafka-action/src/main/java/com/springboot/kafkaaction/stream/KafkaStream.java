package com.springboot.kafkaaction.stream;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.Topology;

import java.util.Properties;

/**
 * @author guowf
 * @mail guowf_buaa@163.com
 * @description:
 * @data created in 2019-06-01 09:27
 * KafkaStream
 */
public class KafkaStream {
    public static void main(String[] args) {
        // 创建topology对象
        Topology topology = new Topology();
        // 增加拓扑属性
        topology.addSource("SOURCE", "first");

//        ProcessorSupplier processorSupplier = () -> new ProcessorSupplierImp();

        topology.addProcessor("PROCESSOR", () -> new ProcessorSupplierImp(), "SOURCE");
        // SINK的TOPIC要与SOURCE的TOPIC不相同，在消费者消费时，也是使用SINK的TOPIC进行消费
        // 如果是相同的情况下，会出现无限循环的情况
        topology.addSink("SINK", "second", "PROCESSOR");
        // 属性
        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9092");
        props.put("application.id", "kafkaStream");

        KafkaStreams kafkaStreams = new KafkaStreams(topology, props);
        kafkaStreams.start();
    }
}
