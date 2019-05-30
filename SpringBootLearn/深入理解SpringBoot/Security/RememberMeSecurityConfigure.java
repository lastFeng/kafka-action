package com.springboot.security.configure.rememberme;

public class RememberMeSecurityConfigure extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http){
		http.authorizeRequests()
				.antMatchers("/admin/**").access("hasRole('ADMIN')")
				
				.and().rememberMe().tokenValiditySeconds(86400).key("remember-me-key")
				
				.and().httpBasic()
				
				.and().authorizeRequests().antMatchers("/**").permitAll()
				
				.and().formLogin().loginPage("login/page")
							.defaultSuccessUrl("/admin/welcome");
	}
}