package com.springboot.rabbitmq.queue.receiver;

@Component
public class RabbitMessageReceiver{
	// 定义监听字符队列名称
	@RabbitListener(queue={"${rabbitmq.queue.msg}"})
	public void receiveMsg(String msg){
		System.out.println("收到消息：【" + msg + "】");
	}
	
	@RabbitListener(queue={"${rabbitmq.queue.user}"})
	public void receiveUser(User user){
		System.out.println("收到消息：【" + user + "】");
	}
}