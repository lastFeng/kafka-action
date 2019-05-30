package com.springboot.rabbitmq.queue.config;

// 创建两个RabbitMQ队列
@Configuration
public class RabbitQueueConfig{
	@Value("${rabbitmq.queue.msg}")
	private String msgQueueName;
	
	@Value("${rabbitmq.queue.user}")
	private String userQueueName;
	
	@Bean
	public Queue createQueueMsg(){
		// 创建字符串消息队列，boolean值代表是否持久化消息
		return new Queue(msgQueueName, true);
	}
	
	@Bean
	public Queue createQueueUser(){
		// 创建用户消息队列，boolean值代表是否持久化消息
		return new Queue(msgQueueName, true);
	}
}