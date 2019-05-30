// 点对点WebSocket，能够解决消息由谁发送，由谁接收的问题
// 依赖： spring-boot-starter-security

/**
	1. Spring Security的配置
**/
@Configuration
@EnableSpringSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
				.authorizeRequests()
				.antMatchers("/", "/login").permitAll()			// 对于路径“/”和“/login”不拦截
				.anyRequest().authenticated()
				.and()
				.formLogin()																// 登录拦截
				.loginPage("/login")												// 成功登录跳转/chat
				.defaultSuccessUrl("/chat")
				.permitAll()
				.and()
				.logout()																		// 退出不拦截
				.permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth
				.inMemoryAuthentication()
				.withUser("wyf").password('wyf').roles("USER")						// 两个用户，均为ROLE_USER组下的用户
				.and()
				.withUser("wisely").password("wisely").roles("USER");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/resources/static/**");
	}
}

/***
	2. WebSocket配置
*/
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry){
		registry.addEndpoint("/endpointWisely").withSockJS();
		regsitry.addEndpoint("/endpointChat").withSockJS();
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry){
		registry.enableSimpleBroker("/queue", "/topic");
	}
}

/**
	3. 控制器
*/
@Controller
public class WSController{
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@MessageMapping("/chat")
	public void handleChat(Principal principal, String msg){
		if(principal.getName().equals("wyf")){
			messagingTemplate.convertAndSendToUser("wisely", "/queue/notifications", principal.getName()+"-send:"+msg)''
		} else{
			messagingTemplate.convertAndSendToUser("wyf", "/queue/notifications", principal.getName()+"-send:"+msg);
		}
	}
}

/**
	4. 增加页面的ViewController
*/
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{
	@Override
	public void addViewControllers(ViewControllerRegistry registry){
		registry.addViiewController("/ws").setViewName("/ws");
		registry.addViiewController("/login").setViewName("/login");
		registry.addViiewController("/chat").setViewName("/chat");
	}
}