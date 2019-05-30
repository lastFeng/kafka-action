package com.springboot.springmvc.converter.pojo;

/***omit imports***/

/**
* 利用JSR-303验证
* 使用注解进行验证
**/
public class ValidatorPojo{
	// 非空判断
	@NotNull(message="id不能为空")
	private Long id;
	
	// 只能是将来的日期---过去日期用：@Past
	@Future(message="需要一个将来日期")
	// 日期转换格式
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull
	private Date date;
	
	// 最小值和最大值
	@NotNull
	@DecimalMin(value="0.1")
	@DecimalMax(value="10000.00")
	private Double doubleValue;
	
	// 限定范围
	@Range(min=1, max=888, message="范围为1到888")
	pirvate Long range;
	
	// 邮箱验证
	@Email(message="邮箱格式错误")
	private String mail;
	
	// 字符串长度限制
	@Size(min=20, max=30, message="字符串长度要求20到30之间)
	private String size;
	
	/***setter and getter***/
}