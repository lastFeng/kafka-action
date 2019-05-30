package com.sprigboot.async.config;

@Configuration
// 启动注解，就可以使用@Async驱动Spring使用异步
@EnableAsync
public class AsyncConfig implements AsyncConfigurer{
	// 定义线程池
	@Override
	public  Executor getAsyncExecutor(){
		// 定义线程池
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		
		// 设置线程属性
		taskExecutor.setCorePoolSize(10);
		taskExecutor.setMaxPoolSize(30);
		taskExecutor.setQueueCapacity(2000);
		
		// 线程池初始化
		taskExecutor.initialize();
		return taskExecutor;
	}
}