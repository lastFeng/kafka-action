package com.springboot.rabbitmq.queue.service.impl;

public class RabbitMqServiceImpl implements ConfirmCallback, RabbitMqService{
	@Value("${rabbitmq.queue.msg}")
	private String msgRouting = null;
	
	@Value("${rabbitmq.queue.user}")
	private String userRouting = null;
	
	// 注入由SpringBoot自动配置的RabbitTemplate
	@Autowired
	private RabbitTemplate rabbitTemplate = null;
	
	// 发送消息
	@Override
	public void sendMsg(String msg){
		System.out.println("发送消息【"+ msg + "】");
		rabbitTemplate.setConfirmCallback(this);
		rabbitTemplate.convertAndSend(msgRouting, msg);
	}
	
	@Override
	public void sendUser(User user){
		System.out.println("发送消息【" + user + "】");
		rabbitTemplate.setConfirmCallback(this);
		rabbitTemplate.convertAndSend(userRouting, user);
	}
	
	// 回调确认方法
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause){
		if(ack){
			System.out.println("消息发送成功");
		}else{
			System.out.println("消息发送失败" + cause);
		}
	}
}