package com.springboot.security.database;
/***omit imports***/
@SpringBootApplication(scanBasePackage="com.springboot.security.database")
public class DataBaseApplication extends WebSecurityConfigureAdapter{
	public static void main(String [] args){
		SpringApplication.run(DataBaseApplication.class, args);
	}
	
	@Autowired
	private DataSource dataSource;
	
	// 使用用户名查询密码
	String pwdQuery = "select user_name, pwd, available from t_user where user_name=?";
	// 使用用户名查询用户信息
	String roleQuery = "select u.user_name, r.role_name from t_user u, t_role r, t_user_role ur whiere u.id=ur.user_id and r.id=ur.role_id and u.user_name=?"
	
	 @Override
	 protected void configure(AutenticationManagerBuilder auth) throw Exception{
	 	PasswordEncoder passwordEncoder = new BCrptyPasswordEncoder();
	 	auth.jdbcAuthentication()
	 			.passwordEncoder(passwordEncoder)
	 			.dataSource(dataSource)
	 			.usersByUsernameQuery(pwdQuery)
	 			.authoritiesByUsernameQuery(roleQuery);
	 }
}