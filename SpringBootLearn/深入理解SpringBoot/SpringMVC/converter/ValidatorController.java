package com.springboot.springmvc.converter.controller;

/***omit imports***/

@Controller
public class ValidatorController{
	@GetMapping("/valid/page")
	public String validPage(){
		return "/validato/pojo";
	}
	
	/**
	* 解析验证参数错误
	* @param vp 需要验证的POJO，使用注解@Valid表示验证
	* @param errors 错误信息，由spring mvc通过验证pojo后自动填充
	* @return 错误信息Map
	*/
	@RequestMapping(value="/valid/validate")
	@ResponseBody
	public Map<String, Object> Validate(@Valid @RequestBody ValidatorPojo vp, Errors errors){
		Map<String, Object> errMap = new HashMap<>();
		// 获取错误列表
		List<ObjectError> objErr = errors.getAllErrors();
		
		for(ObjectError err: objErr){
			String key = null;
			String msg = null;
			
			// 字段错误
			if(err instanceOf FieldError){
				FieldError fe = (FieldError) err;
				key = fe.getField()
			}
			// 非字段错误
			else{
				key = err.getObjectName();
			}
			msg = err.getDefaultMessage();
			errMap.put(key, msg);
		}
		return errMap;
	}
}