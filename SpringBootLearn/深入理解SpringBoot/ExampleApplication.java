package com.springboot.example;

@SpringBootApplication(scanBasePackages="com.springboot.example")
@MapperScan(annotationClass=Mapper.class, basePackages="com.springboot.example")
public class ExampleApplication{
	public static void main(String[] args){
		SpringApplication.run(ExampleApplication.class, args);
	}
}