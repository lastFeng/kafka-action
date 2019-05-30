package com.springboot.redis.cache.main;

/***omit imports***/

@SpringBootApplication(scanBasePackages="com.springboot.redis.cache")
@MapperScan(basePackages="com.springboot.redis.cache", annotationClass=Repository.class)
@EnableCaching
public class RedisCacheMain{
	public static void main(String[] args){
		SpringApplication.run(RedisCacheMain.class, args);
	}
}