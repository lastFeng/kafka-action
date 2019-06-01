package com.springboot.kafkaaction.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * @author guowf
 * @mail guowf_buaa@163.com
 * @description:
 * @data created in 2019-06-01 09:00
 */
public class CustomerProducerInterceptor implements ProducerInterceptor<String, String> {
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
    private int successCount = 0;
    private int errorCount = 0;

    /**
     * 用户对消息进行任意操作：
     * 但是：最好保证不要修改消息所属的topic和partition
     * @param producerRecord 消息记录
     * @return producerRecord
     * 给消息添加一个时间戳
     * */
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
        SimpleDateFormat formatDate = new SimpleDateFormat(FORMAT);
        return new ProducerRecord(producerRecord.topic(), producerRecord.partition(), producerRecord.key(),
                 formatDate.format(System.currentTimeMillis())+ "," + producerRecord.value());
    }

    /**
     * 发送之后，成功或者失败的处理
     * */
    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        if (e == null){
            successCount++;
        }
        else {
            errorCount++;
        }
    }

    /**
     * 关闭
     * */
    @Override
    public void close() {
        System.out.println("发送成功： " + successCount + "条数据");
        System.out.println("发送失败： " + errorCount + "条数据");
    }

    /**
     * 获取配置文件信息属性
     * @param map 配置的map对象
     * */
    @Override
    public void configure(Map<String, ?> map) {

    }
}
