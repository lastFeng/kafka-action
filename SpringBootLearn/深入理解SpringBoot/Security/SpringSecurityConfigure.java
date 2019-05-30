package com.springboot.security.configure.spring;

public class SpringSecurityConfigure extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequest()
				// 使用Spring表达式限定只有角色ROLE_USER或者ROLE_ADMIN可以访问
				.antMatchers("/user/**").access("hasRole('USER') or hasRole('ADMIN')")
				// 设置访问权限给ROLE_ADMIN，要求是完整登录（非记住我登录）
				.antMatchers("/admin/welcome").access("hasAuthority('ROLE_ADMIN') && isFullAuthenticated()")
				// 设置"/admin/welcome2"访问权限给角色ROLE_ADMIN，并允许不完全登录
				.antMatchers("/admin/welcome2").access("hasAuthority('ROLE_ADMIN')")
				.and().remeberMe()
				.and().formLogin()
				.and().httpBasic();
	}
}