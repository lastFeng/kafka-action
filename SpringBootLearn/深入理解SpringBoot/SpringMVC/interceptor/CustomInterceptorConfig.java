package com.springboot.springmvc.interceptor.config

/*** omit imports ***/

/**
* 通过重写WebMvcConfig的addInterceptors方法来注册自定义拦截器
*/

@Configuration
public class CustomInterceptorConfig{
	@Override
	public void addInterceptors(InterceptorRegistry registry){
		InterceptorRegistration iR = registry.addInterceptor(new CustomSimpleInterceptor());
		
		// 设置正则表达式，来进行相应路径下的拦截，只拦截“/interceptor/”下的请求
		iR.addPathPatterns("/interceptor/*")
	}
}