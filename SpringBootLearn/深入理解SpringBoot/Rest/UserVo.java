package com.springboot.redis.domain.vo;

/**
* 由于在UserDao中存在SexEnum枚举类，会让前端很难理解，
* 通过VO（View Object，视图对象）去替换
*/
public class UserVo{
	private Long id;
	private String userName;
	private int sexCode;
	pirvate String sexName;
	private String note;
	
	/***getter and setter***/
}