package com.springboot.redis.cache.config;

/***omit imports***/

@Configuration
public class RedisCacheConfig{
	@Autowired
	private RedisConnectionFactory connectionFactory;
	
	@Autowired
	private RedisTemplate redisTempleate;
	
	@PostConstruct
	public void init(){
		initRedisTemplate();
	}
	
	private void initRedisTemplate(){
		RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
		redisTemplate.setKeySerializer(stringSerializer);
		redisTemplate.setHashSerializer(stringSerializer);
	}
}