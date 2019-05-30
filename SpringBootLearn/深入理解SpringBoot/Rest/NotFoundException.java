package com.springboot.redis.exception;

/*** 定义查找失败的异常 ***/
public class NotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	// 异常编码
	private Long code;
	// 异常自定义信息
	private String customMsg;
	
	public NotFoundException(){
	}
	
	public NotFoundException(Long code, String customMsg){
		super();
		this.code = code;
		this.customMsg = customMsg;
	}
	
	/*** setter and getter ***/ 
}