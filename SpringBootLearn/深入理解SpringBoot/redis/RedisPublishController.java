package com.springboot.redis.publish.controller;

/***omit imports***/

@RestController
public class RedisPublishController{
	@Autowired
	private RedisTemplate redisTemplate;
	
	@GetMapping("/testPublish", produces="application/json;charset=utf-8")
	public Map<String,Object> testPublish(String topicName, Object message){
		/**
		* 使用RedisTemplate的converAndSend(channel, message)函数
		* channel：代表发布的渠道
		* message：代表发布的消息
		**/
		Topic topic = new ChannelTopic(topicName);
		
		redisTemplate.convertAndSend(topic, message);
		
		Map<String, Object> map = new HashMap<>();
		map.put("success", true);
		return map;
	}
}