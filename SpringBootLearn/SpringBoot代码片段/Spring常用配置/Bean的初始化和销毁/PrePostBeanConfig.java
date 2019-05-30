/**
* Java配置：使用@Bean的initMethod和destroyMethod属性配置定义的方法
* 注解配置：利用JSR250的@PostConstruct和@PreDestroy在方法上进行注解------  dependency依赖：jsr-250-api
**/

// @Bean 形式
class BeanWayService{
	public void init(){
		System.out.println("@Bean-init-method");
	}
	
	public void destroy(){
		System.out.println("@Bean-destroy-method");
	}
}

// JSR250形式
class JSR250WayService{
	@PostConstruct
	public void init(){
		System.out.println("@Bean-init-method");
	}
	
	@PreDestroy
	public void destroy(){
		System.out.println("@Bean-destroy-method");
	}
}

public class PrePostBeanConfig{
	@Bean(initMethod="init", destroyMethod="destroy")
	public BeanWayService beanWayService(){
		return new BeanWayService();
	}
	
	@Bean
	pubilc JSR250WayService jsr250WayService(){
		return new JSR250WayService();
	}
}