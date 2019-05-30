@Configuration
@EnableWebMvc
@Component("xxx")
public class MyMvcConfig extends WebMvcConfigurationAdapter{
	@Bean
	public InternalResourceViewResolver viewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/classes/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHanderRegistry registry){
	
		// 设置对外暴露的访问路径，以及文件放置的目录
		registry.addResorceHandler("/assets/**").addResourceLocations("classpath:/assets/")
	}
}