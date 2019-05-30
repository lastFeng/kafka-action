// Spring通过任务执行器（TaskExecutor）来实现多线程和并发编程

// 方法：
// 1. 使用ThreadPoolTaskExecutor实现基于线程池的TaskExecutor
// 2. 开启@EnableAsync来开启异步任务支持，在方法上使用@Async注解来声明异步任务


// 开启异步任务支持
// 通过实现接口来获得一个基于线程池的TaskExecutor
@Configuration
@EnableAsync
public class TaskExecutorConfig implements AsyncConfigurer{
	@Override
	public Executor getAsyncExecutor(){
		THreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(5);			// 设置核心池大小
		taskExecutor.setMaxPoolSize(10);			// 设置最大池大小
		taskExecutor.setQueueCapacity(25);		// 设置队列容量
		taskExecutor.initialize();						// 初始化线程池
		
		return taskExecutor;
	}
	
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler(){
		return null;
	}
}

// 任务执行类
class AsyncTaskService{
	@Async
	public void executeAsyncTask(Integer i){
		System.out.println("执行异步任务：" + i);
	}
	
	@Async
	public void executeAsyncTaskPlus(Integer i){
		System.out.println("执行异步任务：" + (i+1));
	}
}