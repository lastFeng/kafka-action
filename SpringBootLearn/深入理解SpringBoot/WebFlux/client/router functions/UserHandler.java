package com.springboot.webflux.routerfunctions.handler;

@Service
public class UserHandler{
	@Autowired
	private UserRespository userRepository;
	
	private UserVo translate(User user){
		UserVo userVo = new UserVo();
		userVo.setId(user.getId());
		userVo.setUserName(user.getUserName());
		userVo.setSexCode(user.getSex().getCode());
		userVo.setSexName(user.getSex().getName());
		userVo.setNote(user.getNote());
		return userVo;
	}
	
	public Mono<ServerResponse> getUser(ServerRequest request){
		// 获取请求RUI参数
		String idStr = request.pathVariable("id");
		Long id = Long.valueOf(idStr);
		Mono<USerVo> userVoMono = userRepository.findById(id).map(user -> translate(user));
		
		return ServerResponse
									// 响应成功
									.ok()
									.contentType(MediaType.APPLICATION_JSON_UTF8)
									.body(userVoMono, UserVo.class);
	}
	
	public Mono<ServerResponse> insertUser(ServerRequest request){
		Mono<User> userMonoParam = request.bodyToMono(User.class);
		Mono<UserVo> userVoMono = userMonoParam
																	.cache()
																	.flatMap(user -> userRepository.save(user).map(u -> translate(u)));
		return ServerRespones
							.ok()
							.contentType(MediaType.APPLICATION_JSON_UTF8)
							.body(userVoMono, UserVo.class);
	}
	
	public Mono<ServerResponese> updateUser(ServerRequest request){
		Mono<User> userMonoParam = request.bodyToMono(User.class);
		Mono<UserVo> userVoMono = userMonoParam
																		.cache()
																		.flatMap(user -> userRepository.save(user).map(u->translate(u)));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(userVoMomo, UserVo.class);
	}
	
	public Mono<ServerResponse> deleteUser(ServerRequest request){
		String idStr = request.pathVariable("id");
		Long id = Long.valueOf(idStr);
		
		Mono<Void> monoVoid = userRepository.deleteById(id);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(monoVoid, Void.class);
	}
	
	public Mono<ServerResponse> findUsers(ServerRequest request){
		String userName = request.pathVariable("userName");
		String note = request.pathVariable("note");
		
		Flux<UserVo> userVoFlux = userRepository.findByUserNameLikeAndNoteLike(userName, note).map(u -> translate(u));
		
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(userVoFlux, UserVo.class);
	}
	
	public Mono<ServerResponse> updateUserName(ServerRequest request){
		String idStr = request.pathVariable("id");
		Long id = Long.valueOf(idStr);
		
		String userName = request.headers().header("userName").get(0);
		Mono<User> userMono = userRepository.findById(id);
		User user = userMono.block();
		
		user.setUserName(userName);
		Mono<UserVo> result = userRepository.save(user).map(u -> translate(u));
		
		return ServerRespose
								.ok()
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.body(result, UserVo.class);
	}
}