package com.springboot.webflux.routefunctions.config;

// 静态导入
import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
/***other imports omit***/

@Configuration
public class RouterConfig{
	// 注入用户处理器
	@Autowired
	private UserHandler userHandler;
	
	// 请求头用户名称属性
	private static final String HEADER_NAME = "header_user";
	// 请求头密码属性名称
	private static final String HEADER_VALUE = "header_password";
	
	// 用户路由
	@Bean
	pubic RouterFunctions<ServerResponse> userRouter(){
		RouterFunction<ServerResponse> router = 
				// 对应请求URI的对应关系
				route(
					// GET请求及其路径
					GET("/router/user/{id}")
					// 响应结果为数据流
					.and(accpet(APPLICATION_STREAM_JSON)), 
					// 定义处理方法
					userHandler::getUser)
					
					// 增加一个路由
				.andRoute(
					POST("/router/user")
					.and(accept(APPLICATION_STREAM_JSON)),
					userHanler::insertUser)
				
				.andRoute(
					GET("/router/{userName}/{note}")
					.and(accept(APPLICATION_STREAM_JSON)),
					userHandler::findUsers)
					
				.andRoute(
					PUT("/router/user")
					.and(accept(APPLICATION_STREAM_JSON)),
					userHandler::updateUser)
					
				.andRoute(
					DELETE("/router/user/{id}")
					.and(accept(APPLICATION_STREAM_JSON)),
					userHandler::deleteUser)
				.andRoute(
				PUT("/router/user/name")
				.and(accept(APPLICATION_STREAM_JSON)),
				userHandler::updateUserName);
		return router;
	}
	
	@Bean
	public RouterFunction<ServerResponse> securityRouter(){
		RouterFunction<ServerResponse> router = 
					route(
						GET("/security/user/{id}")
						.and(accept(APPLICATION_STREAM_JSON)),
						userHandler::getUser
					).filter((request, next) -> filterLogic(request, next));
		return router;
	}
	
	private Mono<ServerResponse> filterLogic(ServerRequest request, HandlerFunction<ServerResponse> next){
		// 取出请求头
		String userName = request.headers().header(HEADER_NAME).get(0);
		String password = request.headers().header(HEADER_VALUE).get(0);
		
		// 验证通过的条件
		if(!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password) && !userName.equals(password)){
			return next.handle(request);
		}
		
		return ServerResponse.status(HttpStatus.UNAUTHORIZED).build();
	}
}