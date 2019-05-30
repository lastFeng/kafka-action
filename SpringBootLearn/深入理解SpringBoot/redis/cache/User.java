package com.springboot.redis.cacha.pojo;

/***omit imports***/

// 使用AliasFor执行Mybatis的别名设置
@AliasFor("user")
public class User implements Serializable{
	private fianl static long serialVersionUID = 1L;
	private Long id;
	private String userName;
	private String note;
	
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public  String getUserName(){
		return this.userName;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	pubilc String getNote(){
		return this.note;
	}
	
	pubic void setNote(String note){
		this.note = note;
	}
}