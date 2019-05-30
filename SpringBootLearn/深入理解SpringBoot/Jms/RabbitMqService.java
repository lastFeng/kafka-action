package com.springboot.rabbitmq.queue.service;

public interface RabbitMqService{
	public void sendMsg(String msg);
	public void sendUser(User user);
}