package com.springboot.redis.cache.dao;
/****omit imports***/

@Repository
public interface UserDao{
	// 获取单个用户
	User getUserById(Long id);
	
	// 新增用户
	int insertUser(User user);
	
	// 修改用户
	int updateUser(User user);
	
	// 查询用户，指定mybatis的参数名称
	List<User> findUserListByUserNameAndnote(@Param("userName") String userName, @Param("note") String note);
	
	// 删除用户
	int deleteUserById(Long id);
}