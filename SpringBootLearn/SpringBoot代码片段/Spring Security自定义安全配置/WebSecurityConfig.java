// 通过显示配置的方式来自定义安全配置

// 通过扩展类WebSecurityConfigurerAdapter
@Configuration
@EnableWebSecurity
public WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private ReaderRpository readerRpository;
	
	// 定义访问url的权限
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
				.authorizeRequests()
				.antMatchers("/").access("hasRole('READER')")
				.antMatchers("/**").permitAll()
				
				.and()
				
				.formLogin()
				.loginPage("/login")
				.failureUrl("/login?error=true");
	}
	
	// 定义角色用户
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth
				.userDetailsService(new UserDetailsService(){
					@Override
					public UserDetails loadUserByUserName(String userName) throws UsernameNotFountException{
						return readerRepository.findOne(userName);
					}
				});
	}
}