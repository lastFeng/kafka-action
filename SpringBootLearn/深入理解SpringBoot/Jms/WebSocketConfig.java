package com.springboot.stomp.config

@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	// 注册服务器端点
	@override
	public void registerStompEndpoints(StompEndpointRegistry registry){
		// 增加一个聊天服务端点
		registry.addEndpoint("/socket").withSockJS();
		registry.addEndpoint("/wsuser").withSockJS();
	}
	
	// 定义服务器端点请求和订阅前缀
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry){
		// 客户端订阅路径前缀
		registry.enableSimpleBroker("/sub", "/queue");
		// 服务端点请求前缀
		registry.setApplicationDestinationPrefixes("/request");
	}
}