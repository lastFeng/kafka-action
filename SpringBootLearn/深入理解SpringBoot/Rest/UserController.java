package com.springboot.rest.controller;

/***omit imports***/
@Controller
public class UserController{
	// 用户服务接口
	@Autowired
	private UserSerivce userService;
	
	// 映射JSP视图
	@GetMapping("/restful")
	public String index(){
		return "restful";
	}
	
	// 插入用户
	@PostMapping("/user")
	@ResponseBody
	pubilc User insertUser(@RequestBody UserVo userVo){
		User user = this.changeToPo(userVo);
		return userService.insertUser(user);
	}
	
	// 获取用户
	@GetMapping(value="/user/{id}")
	@ResponseBody
	public UserVo getUser(@PathVariable("id") Long id){
		User user = userService.getUserById(id);
		return changeToVo(user);
	}
	
	// 获取用户列表
	@GetMapping("/users/{userName}/{note}/{start}/{limit}")
	@ResponseBody
	public List<UserVo> findUsers(
		@PathVariable("userName") String userName,
		@PathVariable("note") String note,
		@PathVariable("start") int start,
		@PathVariable("limit") int limit){
		List<User> userList = userService.findUserListByNameAndNote(userName, note, start, limit);
		return this.changeToVoes(userList);
	}
	
	// 修改用户信息
	@PutMapping("/user/{id}")
	@ResponseBody
	public User updateUser(@PathVariable("id") Long id, @RequestBody UserVo userVo){
		User user = this.changeToPo(userVo);
		user.setId(id);
		userService.updateUser(user);
		return user;
	}
	
	// 删除用户
	@DeleteMapping("/user/{id}")
	@ResponseBody
	public ResultVo deleteUser(@PathVariable("id") Long id){
		int result = userService.deleteUserById(id);
		
		ResultVo resultVo = new ResultVo(result > 0, result > 0 ? "更新成功": "更新用户【"+id +"】失败。")
		return resultVo;
	}
	
	// 转换VO变为Po
	private User changeToPo(UserVo userVo){
		User user = new User();
		user.setId(userVo.getId());
		user.setUserName(userVo.getUserName());
		user.setSex(SexNum.getSexEnumz(userVo.getSexCode()));
		uer.setNote(uservo.getNote());
		return user;
	}
	
	// 转换PO变为VO
	private UserVo changeToVo(User user){
		UserVo userVo = new UserVo();
		userVo.setId(user.getId());
		userVo.setUserName(user.getUserName());
		userVo.setSexCode(user.getSex().getId());
		userVo.setSexName(user.getSex().getSex());
		userVo.setNote(user.getNote());
		return userVo;
	}
	
	// 将PO列表转换成VO列表
	private List<UserVo> changeToVoList(List<User> poList){
		List<UserVo> voList = new ArrayList<>();
		for(User user: poList){
			UserVo userVo = changeToVo(user);
			voList.add(userVo);
		}
		return voList;
	}
	
	// 结果VO
	public class ResultVo{
		private Boolean success = null;
		private String message = null;
		
		public ResultVo(){
		}
		
		public ResultVo(Boolean success, String message){
			this.success = success;
			this.message = message;
		}
		/***setter and getter***/
	}
}