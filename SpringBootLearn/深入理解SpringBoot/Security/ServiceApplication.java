package com.springboot.security.service.main;

@SpringBootApplication(scanBasePackages="com.springboot.security.service")
@MapperScan(basePackages="com.springboot.security", annotationClass=Mapper.class)
@EnableCaching
public class ServiceApplication extends WebSecurityConfigureAdapter{
	public static void main(String[] args){
		SpringApplication.run(ServiceApplication.class, args);
	}
	
	@Value("${system.user.password.secret}")
	private String secret;
	
	@Autowired
	private UserDetailsService userDetailsService;
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder(secret);
		auth.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder);
	}
}
