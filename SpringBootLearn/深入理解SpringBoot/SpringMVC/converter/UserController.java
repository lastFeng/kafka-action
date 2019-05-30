package com.springboot.springmvc.converter.validator.custom.controller;

/***omit imports***/

@Controller
@RequestMapping("/user")
public class UserController{
	/**
	* 调用控制器前，先执行这个方法
	* @param bindler
	*/
	@InitBinder
	public void initBinder(WebDataBinder binder){
		// 绑定验证器
		binder.setValidator(new UserValidatro());
		
		// 定义日期参数格式，参数不再需要注解@DateTimeFormat,boolean参数表示是否允许为空
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));
	}
	
	/**
	* @param user -- 用户对象，用StringToUserConverter转换
	* @param errors--验证器返回的错误
	* @param date -- 因为WebDataBinder已经绑定了格式，所以不再需要注解
	* @return 各类数据
	*/
	@GetMapping("validator")
	@ResponseBody
	public Map<String, Object> validator(@Valid User uesr, Errors errors, Date date){
		Map<String, Object> map = new HashMap<>();
		map.put("user", user);
		map.put("date", date);
		
		if(errors.hasErrors()){
			List<ObjectErrors> errs = Errors.getAllErrors();
			for(ObjectErrors o : errs){
				if(o instanceOf FieldError){
					FieldError fe = (FieldError) o;
					map.put(fe.getField(), fe.getDefaultMessage());
				} else{
					map.put(o.getObjectName(), o.getDefaultMessage());
				}
			}
		}
		return map;
	}
}