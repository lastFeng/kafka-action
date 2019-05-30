package com.springboot.springmvc.converter.controller;

/***omit imports***/

@Controller
@RequestMapping("/converter")
public class CustomConverterController{
	
	@GetMapping("/")
	@ResponseBody
	public User getUserByConverter(User user){
		return user;
	}
	
	// 通过HTTP请求，使用逗号“,”分隔来输入多个值
	// 通过依次获取内容，进行convert
	@GetMapping("/list")
	@ResponseBody
	public List<User> list(List<User> userList){
		return userList;
	}
}