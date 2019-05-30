package com.springboot.chapter6.service.impl;

public class UserBatchServiceImpl implements UserBatchService{
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public int insertUsers(List<User> userList){
		int count = 0;
		
		for(User user: userList){
			// 调用子方法，将使用@Transactional定义的传播行为
			count += userService.insertUser(user);
		}
		return count;
	}
}