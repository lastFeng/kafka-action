package com.springboot.springmvc.interceptor.custom;

/***omit imports***/

public class CustomSimpleInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandler(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("-------- 处理器前方法-----------");
		return true;
	}
	
	@Override
	public void postHandler(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception(){
		System.out.println("---------处理器后方法--------------");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception{
		System.out.println("------------处理器完成方法-----------------");
	}
}