// 通过继承AbstractAnnotationConfigDispatchServletInitializer来配置DispatchServlet

package spittr.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatchServletInitializer;

public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatchServletInitializer{
	// 处理器映射
	@Override
	protected String[] getServletMappings(){
		return new String[] {"/"};
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses(){
		return new Class<?>[] {RootConfig.class};
	}
	
	// 指定配置类
	@Override
	protected Class<?>[] getServletConfigClasses(){
		return new Class<?>[] {WebConfig.class};
	}
}


// 最小但可用的SpringMvc配置类
package spittr.config;

@Configuration
@EnableWebMvc
@ComponentScan("spittr.web")

public class WebConfig extends WebMvcConfigurerAdapter{
	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}
	
	// 处理静态资源
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
			configurer.enable();
	}
}

// RootConfig.class
@Configuration
@ComponentScan(basePackages={"spittr"}, excludeFilter={@Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)})
public class RootConfig{
	
}