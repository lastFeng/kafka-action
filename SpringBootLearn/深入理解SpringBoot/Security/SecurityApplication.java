package com.springboot.security.inmemoryauthentication;

/***omit imports***/
@SpringBootApplication(scanBasePackages="com.springboot.security")
public class SecurityApplication extends WebSecurityConfigureAdapter {
	// 重写方法，来配置用户签名服务
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		// 密码编码器
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		// 使用内存存储
		auth.inMemoryAuthentication()
				// 设置密码编码器
				.passwordEncoder(passwordEncoder)
				// 注册用户admin，密码为abc，并赋予USER和ADMIN的角色权限
				.withUser("admin")
				// 通过passwordEncoder.encode("abc")得到加密后的密码
				.password("...")
				.roles("USER", "ADMIN")
			.and()
				// 注册用户myuser，密码为123456，并赋予USER的角色权限
				.withUser("myuser")
				.password("...")
				.roles("USER");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		// 密码编码器
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> userConfig = 
					auth.inMemoryAuthentication()
							.passwordEncoder(passwordEncoder);
							
		// 注册用户
		userConfig.withUser("admin")
							.password("...")
							.authorities("ROLE_USER", "ROLE_ADMIN");
							
		// 注册用户
				userConfig.withUser("myuser")
							.password("...")
							.authorities("ROLE_USER");
	}
}