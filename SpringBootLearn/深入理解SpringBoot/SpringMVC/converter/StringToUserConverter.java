package com.springboot.sprinbmvc.converter.custom;

/**omit imports**/

// 自定义转换器，<source, destination>
@Component
public class StringToUserConverter implements Converter<String, User>{
	/**
	* 转换方法
	* userStr的信息格式是：{id}-{userName}-{note}
	**/
	@Override
	public User convert(String userStr){
		User user = new User();
		String[] strArr = userStr.split("-");
		Long id = strArr[0];
		String userName = strArr[1];
		String note = strArr[2];
		user.setId(id);
		user.setUserName(userName);
		user.setNote(note);
		return user;
	}
}