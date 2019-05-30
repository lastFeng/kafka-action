package com.springboot.rest.exception;

@ControllerAdvice(
	basePackages = {"com.springboot.rest.controller.usercontroller.*"},
	annotations = {Controller.class, RestController.class})
public class VoControllerAdevice{
	// 异常处理，可以定义异常类型进行拦截处理
	@ExceptionHandler(value=NotFoundException.class)
	// 以JSON表达方式响应
	@ResponseBody
	// 定义为服务器错误状态码
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> exception(HttpServletRequest request, NotFoundException ex){
		Map<String, Object> msgMap = new HashMap<>();
		// 获取异常信息
		msgMap.put("code", ex.getCode);
		msgMap.put("message", ex.getCustomMsg());
		return msgMap;
	}
}