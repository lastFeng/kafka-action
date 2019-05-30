package com.springboot.redis.cache.controller;

/***omit imports***/

@Controller
@RequestMapping("/user")
public class UserCacheController{
	@Autowired
	private UserService userservice;
	
	@RequestMapping("/getUserById")
	@ResponseBody
	public User getUserById(Long id){
		return userService.getUserById(id);
	}
	
	@RequestMapping("/insertUser")
	@ResponseBody
	public User insertUser(String userName, String note){
		User user = new User();
		user.setUserName(userName);
		user.setNote(note);
		userService.insertUser(user);
		return user;
	}
	
	@RequestMapping("/updateUserName")
	@ResponseBody
	public Map<String, Object> updateUserName(Long id, String userName){
		User user = userService.updateUserName(id, userName);
		String isSuccess = user == null ? "更新失败" : "更新成功";
		Map<String, Object> map = new HashMap<>();
		map.put("status", isSuccess);
		return map;
	}
	
	@RequestMapping("/deleteUserById")
	@ResponseBody
	public Map<String, Object> deleteUserById(Long id){
		int result = userService.deleteUserById(id);
		String isSuccess = result == 1 ? "删除成功" : "删除失败";
		Map<String, Object> map = new HashMap<>();
		map.put("status", isSuccess);
		return map;
	}
}