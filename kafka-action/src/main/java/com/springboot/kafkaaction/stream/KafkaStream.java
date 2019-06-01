package com.springboot.kafkaaction.stream;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorSupplier;

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

        topology.addSink("SINK", "second", "PROCESSOR");
        // 属性
        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9092");
        props.put("application.id", "kafkaStream");

        KafkaStreams kafkaStreams = new KafkaStreams(topology, props);
        kafkaStreams.start();
    }
}
