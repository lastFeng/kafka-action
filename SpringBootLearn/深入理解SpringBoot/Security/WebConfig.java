package com.springboot.security.configure;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	// 增加映射关系
	@Override
	public void addViewControllers(ViewControllerRegistry registry){
		// 使得/login/page映射为login.jsp
		registry.addViewController("/login/page").setViewName("login");
		// 使得/logout/page映射为logout_welcome.jsp
		registry.addViewController("/logout/page").setViewName("logout_welcome");
		// 使得/logout 映射为logout.jsp
		registry.addViewController("/logout").setViewName("logout");
	}
}