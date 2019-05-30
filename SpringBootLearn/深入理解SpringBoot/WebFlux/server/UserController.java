package com.springboot.webflux.controller;

@RestController
public class UserController{
	@Autowired
	private UserService userService;
	
	// 获取用户
	@GetMapping("/user/{id}")
	public Mono<UserVo> getUser(@PathVariable Long id){
		return userService.getUser(id)
											.map(u --> translate(u));
	}
	
	// 新增用户
	@PostMapping("/user")
	public Mono<UserVo> insertUser(@RequestBody User user){
		return userService.insertUser(user).map(u -> translate(u));
	}
	
	// 更新用户
	@PutMapping("/user")
	public Mono<UserVo> updataUser(@RequestBody User user){
		return userService.updateUser(user).map(u->translate(u));
	}
	
	// 删除用户
	@DeleteMapping("/user/{id}")
	public Mono<Void> deleteUser(@PathVariable Long id){
		return userService.deleteUser(id);
	}
	
	// 查询用户
	@GetMapping("/user/{userName}/{note}")
	public Flux<UserVo> findUsers(@PathVariable String userName, @PathVariable String note){
		return userService.findUsers(userName, note).map(u -> translate(u));
	}
	
	/**
	* 完成PO到VO的转换
	* @param user    po持久层对象
	* @return UserVo vo视图对象
	*/ 
	private UserVo translate(User user){
		UserVo vo = new UserVo();
		vo.setId(user.getId());
		vo.setUserName(user.getUserName());
		vo.setSexCode(user.getSex().getCode());
		vo.setSexName(user.getSex().getName());
		vo.setNote(user.getNote());
		return vo;
	}
}