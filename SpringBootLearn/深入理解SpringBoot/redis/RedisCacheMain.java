package com.springboot.redis.cache.main;

/***omit imports***/
// 启用@EnableCaching注解来驱动Spring缓存机制

@SpringBootApplication(scanBasePackages="com.springboot.redis.cache")
@MapperScan(basePackages="com.springboot.redis.cache", annotationClass=Repository.class)
@EnableCaching
public class RedisCacheMain{
	public static void main(String[] args){
		SpringApplication.run(RedisCacheMain.class, args);
	}
}