// Websocket广播式即服务端有消息时，会将消息发送给所有连接了当前endpoint的浏览器
/**
	过程如下：
		1. 配置WebSocket：需要在配置类上使用@EnableWebSocketMessageBroker,来开启WebSocket支持
			并继承AbstractWebSocketMessageBrokerConfig类，重写其方法来配置WebSoket
		
		// 使用STOMP协议来传输基于代理（message broker）的消息，控制器可以支持@MessageMapping
		@EnableWebSocketMessageBroker
		@Configuration
		public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{
		
		// 注册STOMP协议的节点（endpoint），并映射的指定URL
			@Override
			public void registerStompEndpoints(StompEndpointRegistry registry){
				// 注册endpoint，并制定使用SockJS协议
				registry.addEndpoint("/endpointWisely").withSockJS();
			}
			
			// 配置消息代理（Message broker）
			@Override
			public void configureMessageBroker(MessageBrokerRegistry registry){
				// 广播式配置一个/topic消息代理
				registry.enableSimpleBroker("/topic");
			}
		}
**/

/**
		2. 浏览器向服务端发送的消息用此类接受
		public class BroadcastFromClientMessage{
			private String name;
			public String getName(){
				return name;
			}
		}
*/

/**
		3. 服务端向浏览器发送的此类消息
		public class BroadcastFromServerMessage{
			private String respoonseMessage;
			public BroadcastFromServerMessage(String responseMessage){
				this.responseMessage = responseMessage;
			}
			
			public String getResponseMessage(){
				return this.responseMessage;
			}
		}
*/

/***
		4. 演示控制器
		@RestController
		pulbic class WsController{
			@MessageMapping("/welcome")   // 映射地址
			@SendTo("/topic/getResponse")	// 订阅了该路径下的浏览器发送消息
			public BroadcastFromServerMessage say(BroadcastFromClientMessage message) throws Exception{
				Thread.sleep(1000);
				return new BroadcastFromServerMessage("Welcome" + message.getName() + "!");
			}
			
		}
***/