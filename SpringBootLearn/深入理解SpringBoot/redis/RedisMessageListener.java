package com.springboot.redis.publish.listener;

/***omit imports***/

/***定义消息监听器，用来监听渠道发送过来的消息***/
@Component
public class RedisMessageListener implements MessageListener{
	/**
	* 得到渠道的消息后，进行处理的方法
	* @param message Redis发送过来的消息
	* @param pattern 渠道名称
	**/
	@Override
	public void onMessage(Message message, byte[] pattern){
		// 消息体
		String body = new String(message.getBody());
		// 渠道名称
		String topic = new String(pattern);
		
		System.out.println(body);
		System.out.println(topic);
	}
}