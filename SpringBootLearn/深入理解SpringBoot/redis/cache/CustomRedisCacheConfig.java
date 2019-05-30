package com.springboot.redis.cache.custom.config;

/***omit imports***/
@Configuration
public class CustomRedisCacheConfig{
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
	
	// 自定义Redis缓存管理器
	@Bean("redisCacheManager")
	public RedisCacheManager redisCacheManager(){
		// Redis加锁的写入器
		RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(connectionFactory);
		
		// 启动Redis缓存的默认设置
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
		
		// 设置JDK序列化器
		config = config.serializeValuesWith(
							SerializationPair.fromSerializer(new JdkSerializationRedisSerializer()));
		
		// 禁用前缀
		config = config.disableKeyPrefix();
		
		// 设置10min超时
		config = config.entryTtl(Duration.ofMinutes(10));
		
		// 创建Redis缓存管理器
		RedisCacheManager redisCacheManager = new RedisCacheManager(writer, config);
		
		return redisCacheManager;
	}
}