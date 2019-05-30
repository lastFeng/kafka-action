package com.springboot.chapter6.controller;

/***omit imports***/

public class UserController{
	// ×¢ÈëService
	@Autowired
	public UserService userService = null;
	
	@RequestMapping(value="/user")
	@ResponseBody
	public User getUserById(Long id){
		return userService.getUserById(id);
	}
	
	@RequestMapping(value="insertUser")
	@ResponseBody
	public Map<String, Object> insertUser(String userName, String note){
		User user = new User();
		
		user.setUserName(userName);
		user.setNote(note);
		
		int update = userService.insertUser(user);
		Map<String, Object> result = new HashMap<>();
		result.put("success", update=1);
		result.put("user", user);
		return result;
	}
}