package com.springboot.springmvc.converter.validator.custom;

/***omit imports***/

public class UserValidator implements Validator{
	// 验证器只支持User类验证
	@Override
	public boolean support(Class<?> clazz){
		return clazz.equals(User.class);
	}
	
	@Override
	public void validate(Object target, Errors errors){
		// 对象为空
		if(target == null){
			// 直接在参数处报错，这样就不能进入控制器的方法
			errors.rejectValue("", null, "用户不能为空");
			return ;
		}
		
		// 强制转换
		User user = (User)target;
		// 用户名非空串
		if(StringUtils.isEmpty(user.getUserName())){
			// 增加错误，可以进入控制器方法
			errors.rejectValue("userName", null, "用户不能为空");
		}
	}
}