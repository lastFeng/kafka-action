package com.springboot.chapter6.service;

/***omits imports***/

public interface UserService{
	// 获取用户信息
	public User getUserById(Long id);
	
	// 新增用户
	public int insertUser(User user);
}