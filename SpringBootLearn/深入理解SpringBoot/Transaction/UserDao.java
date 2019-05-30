package com.springboot.chapter6.dao;

/***--imports--***/

@Repository
public interface UserDao{
	User getUserById(Long id);
	int insertUser(User user);
}