package com.springboot.redis.cache.service;

/***omit imports***/

public interface UserService{
	// 查询单个用户
	User getUserById(Long id);
	
	// 新增
	User insertUser(User user);
	
	// 修改
	User updateUserName(Long id, String userName);
	
	// 查询
	List<User> findUserListByUserNameAndnote(String userName, String note);
	
	// 删除
	int deleteUserById(Long id);
}