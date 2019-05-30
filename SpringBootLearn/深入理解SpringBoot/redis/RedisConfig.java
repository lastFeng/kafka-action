package com.springboot.redis.config

/***omit imports***/

public class RedisConfig{
	private RedisConnectionFactory connectionFactory = null;
	
	@Bean(name="RedisConnectionFactory")
	public RedisConnectionFactory initRedisConnectionFactory(){
		if(connectionFactory != null){
			return connectionFactory;
		}
		
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		
		// 最大空闲数
		poolConfig.setMaxIdle(30);
		
		// 最大连接数
		poolConfig.setMaxTotal(50);
		
		// 最大毫秒数
		poolConfig.setMaxWaitMillis(10000);
		
		// 创建Jedis连接工厂
		JedisConnectionFactory connectionFaction = new JedisConnectionFactory(poolConfig);
		
		// 获取单机的Redis设置
		RedisStandaloneConfiguration rsCfg = connectionFactory.getStandaloneConfiguration();
		
		// 设置相应内容
		connectionFactory.setHostName("127.0.0.1");
		connectionFactory.setPort(6379);
		connectionFactory.setPassword("123456");
		
		this.connectionFactory = connectionFactory;
		return connectionFactory;
	}
}