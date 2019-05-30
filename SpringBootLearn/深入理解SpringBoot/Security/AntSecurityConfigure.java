package com.springboot.security.configure.ant;

pubic class AntSecurityConfigure extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		// 限定签名后的权限
		http
				// 第一段
				.authorizeRequests()
				// 限定“/user/welcome”请求赋予角色ROLE_USER后者ROLE_ADMIN
				.antMatchers("/user/welcome", "/user/details").hasAnyRole("USER","ADMIN")
				// 限定“/admin/”下所有请求权限赋予角色ROLE_ADMIN
				.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
				// 其他路径允许签名后访问
				.anyRequests().permitAll()
				
				// 第二段
				// and代表连接词
				// 对没有配置权限的其他请求允许匿名访问
				and().anonymous()
				
				// 第三段
				// 使用Spring Security默认的登录页面
				.and().formLogin()
				// 启动HTTP基础验证
				.and().httpBasic();
	}
}