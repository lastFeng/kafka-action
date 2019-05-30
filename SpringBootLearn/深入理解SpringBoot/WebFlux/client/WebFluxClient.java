package com.springboot.webflux.client.main;

public class WebFluxClient{
	public static void main(String[] args){
		// 创建WebClient对象，并且设置请求基础路径
		WebClient client = WebClient.create("http://localhost:8080");
		// 一个新的用户
		User newUser = new User();
		newUser.setId(2L);
		newUser.setUserName("user_name_new");
		newUser.setSex(SexEnum.MALE);
		newUser.setNot("note_new");
		
		// 新增用户
		insertUser(client, newUser);
		// 获取用户
		getUser(client, 2L);
		
		User updUser = new User();
		updUser.setId(1L);
		updUser.setNote("note_update");
		updUser.setUserName("user_name_update");
		updUser.setSec(SexEnum.FEMALE);
		
		// 更新用户
		updateUser(client, updUser);
		// 查询用户
		findUsers(client, "user", "note");
		// 删除用户
		deleteUser(client, 2L);
	}
	
	private static void insertUser(WebClient client, User newUser){
		// 注意，者只是定义一个时间，并不会发送请求
		Mono<UserVo> userMono = 
		// 定义POST请求
		client.post()
					// 设置请求uri
					.uri("/user")
					// 请求体为JSON数据流
					.contentType(MediaType.APPLICATION_STREAM_JSON)
					// 请求体内容
					.body(Mono.just(newUser), User.class)
					// 接受请求结果类型
					.accept(MediaType.APPLICATION_STREAM_JSON)
					// 设置请求结果检索规则
					.retrieve()
					// 将结果体转换为一个Mono封装的数据流
					.bodyToMono(UserVo.class);
		// 获取服务器发布的数据流，此时才会发送数据
		UserVo user = userMono.block();
		System.out.println("【用户名称】：" + user.getUserName());
	}
	
	private static void getUser(WebClient client, User newUser){
		Mono<UserVo> userMono = 
				// 定义GET请求
				client.get()
							// uri
							.uri("/user/{id}", id)
							.accept(MediaType.APPLICATION_STREAM_JSON)
							.retrieve()
							.bodyToMono(UserVo.class);
		
		UserVo user = userMono.block();
		System.out.println("【用户名称】：" + user.getUserName());					
	}
	
	private static void updateUser(WebClient client, User updUser){
		Mono<UserVo> userMono = 
					client.put()
					.uri("/usrer")
					.contentType(MediaType.APPLICATION_STREAM_JSON)
					.body(Mono.just(updUser, User.class))
					.accept(MediaType.APPLICATION_STREAM_JSON)
					.retrieve()
					.bodyToMono(UserVo.class);
		UserVo user = userMono.block();
		System.out.println("【用户名称】：" + user.getUserName());		
	}
	
	private static void findUsers(WebClient client, String userName, String note){
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userName", userName);
		paramMap.put("note", note);
		Flux<UserVo> userFlux = 
				client.get().uri("/user/{userName}/{note}", paramMap)
							.accept(MediaType.APPLICATION_STREAM_JSON)
							.retrieve()
							.bodyToFlux(UserVo.class);

		// 通过Iterator遍历
		Iterator<UserVo> iterator = userFlux.toIteratable().interator();
		
		// 遍历
		while(iterator.hasNext()){
			UserVo item = iterator.next();
			System.out.println("【用户名称】：" + item.getUserName());					
		}
	}
	
	private static void deleteUser(WebClient client, Long id){
		Mono<UserVo> userMono = client.delete().uri("/user/{id}", id)
																	.accept(MediaType.APPLICATION_STREAM_JSON)
																	.retrieve()
																	.bodyToMono(UserVo.class);
		Void voidResult = userMono.block();
		System.out.println(voidResult);
	}
}