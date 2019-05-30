public class WebInitializer implements WebApplicationInitializer{
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException{
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		
		context.register(MyMvcConfig.class);
		context.setServletContext(servletContext);
		
		// 注册DispatcherServlet
		Dynamic servletContext.adServlet("dispatcher", new DispatcherServlet(context));
		
		// 添加Servlet的映射
		servlet.addMapping("/");
		// 设置该Servlet的加载顺序
		servlet.setLoadOnStartup(1);
	}
}