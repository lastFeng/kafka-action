/**
	拦截器实现对每个请求处理前后进行相关的业务处理
		1.可以让普通的Bean实现HandlerInterceptor接口或继承HandlerInterceptorAdapter类来实现自定义拦截器
		2. 通过重写WebMvcConfigurerAdapter的addIntercptors来注册自定义拦截器（不会覆盖其他已定义拦截器）
**/

// 自定义拦截器，继承HandlerInterceptorAdapter类来实现
// 重写preHandler方法，在请求发生前执行
// 重写postHandler方法，在请求完成后执行
class CustomInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		Long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		return true;
	}
	
	@Override
	public void postHandler(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
		Long endTime = System.currentTimeMillis();
		Long startTime = (Long)request.getAttribute("startTime");
		request.removeAttribute("startTime");
		
		System.out.println("本次请求处理时间为：" + (endTime - startTime) + "ms");
		
		request.setAttribute("handleTime", endTime-startTime);
	}
}

// 配置
@Configuration
@EnableWebMvc
public CustomInterceptorConfig extends WebMvcConfigurerAdapter{
	@Bean
	public CustomInterceptor customInterceptor(){
		return new CustomInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(customInterceptor());
	}
}