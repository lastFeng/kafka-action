package com.springboot.rabbitmq.queue.controller;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitMqController{
	@Autowired
	private RabbitMqService rabbitMqService;
	
	@GetMapping("/msg")
	public Map<String, Object> msg(String message){
		rabbitMqService.sendMsg(message);
		return resultMap("message", message);
	}
	
	@GetMapping("/user")
	public Map<String, Object> user(Long id, String userName, String note){
		User user = new User(id, userName, note);
		rabbitMqService.sendUser(user);
		return resultMap<"user", user);
	}
	
	private Map<String, Object> resultMap(String key, Object obj){
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);
		result.put(key, obj);
		return result;
	}
}