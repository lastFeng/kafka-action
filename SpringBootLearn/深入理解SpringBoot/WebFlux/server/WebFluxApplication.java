package com.springboot.webflux.server.main;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableReactiveMongoRepository(basePackages="com.springboot.webflux.repository")
public class WebFluxApplication{
	public static void main(String[] args){
		SpringApplication.run(WebFluxApplication.class, args);
	}
}