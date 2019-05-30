package com.springboot.chapter6.service.impl;

public class UserServiceImple implements UserService{
	@Autowired
	private UserDao userDao = null;
	
	// 需要启动事务机制
	@Override
	@Transcational(isolation=Isolation.READ_COMMITTED, timeout=10)
	public int insertUser(User user){
		return userDao.insertUser(user);
	}
	
	// 新增用户
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED, timeout=10)
	public User getUserById(Long id){
		return userDao.getUserById(id);
	}
}