package com.springboot.webflux.pojo;

@Document
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	// gender
	private SexEum sex;
	
	@Field("user_name")
	private String userName;
	private String note;
	
	/***setter and getter***/
}