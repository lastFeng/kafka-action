package com.springboot.kafkaaction.stream;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;

/**
 * @author guowf
 * @mail guowf_buaa@163.com
 * @description:
 * @data created in 2019-06-01 09:36
 */
public class ProcessorSupplierImp implements Processor<byte[], byte[]> {

    private ProcessorContext processorContext;

    @Override
    public void init(ProcessorContext processorContext) {
        this.processorContext = processorContext;
    }

    /**
     * 处理
     * */
    @Override
    public void process(byte[] key, byte[] value) {
        String line = new String(value);

        // 具体要实现的功能：在这里是去除">>>"
        line = line.replace(">>>", "");

        value = line.getBytes();

        // 将修改后的消息进行传递
        processorContext.forward(key, value);
    }

    @Override
    public void close() {
    }
}
