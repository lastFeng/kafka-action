package com.springboot.redis.publish.main;

/***omit imports***/

// 监听Redis发布的消息
@SpringBootApplication(scanBasePackages="com.springboot.redis.publish")
@MapperScan(basePackages="com.springboot.redis.publish", annotationClass=Repository.class)
public class RedisPublishMain{
	@Autowired
	private RedisTemplate redisTemplate = null;
	
	// Redis连接工厂
	@Autowired
	private RedisConnectionFactory redisConnectionFactory = null;
	
	// Redis 消息监听器
	@Autowired
	private RedisMessageListener redisMessageListener = null;
	
	// 任务池
	@Autowired
	private ThreadPoolTaskScheduler taskScheduler = null;
	
	/**
	* 创建任务池，运行线程等待处理Redis的消息
	* @return
	**/ 
	@Bean
	public ThreadPoolTaskScheduler initTaskScheduler(){
		if(taskScheduler != null){
			return taskScheduler;
		}
		
		taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(20);
		
		return taskScheduler;
	}
	
	/**
	* 定义Redis监听容器
	* @return
	**/
	@Bean
	public RedisMessageListenerContainer initRedisContainer(){
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		
		// Redis连接工厂
		container.setConnectionFactory(redisConnectionFactory);
		
		// 设置运行任务池
		container.setTaskExecutor(initTaskScheduler());
		
		// 定义监听渠道，名称为topic1
		Topic topic = new ChannelTopic("topic1");
		
		// 使用监听器监听Redis消息
		container.addMessageListener(redisMessageListener, topic);
		
		return container;
	} 
}