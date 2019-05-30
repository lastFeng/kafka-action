// Condition接口的参数说明：
// 	ConditionContext：
				BeanDefinitionRegistry getRegistry();
				ConfigurableListableBeanFactory getBeanFactory();   --> 检查bean是否存在，甚至探查bean的属性
				Environment getEnvironment();
				ResourceLoader getResourceLoader();
				ClassLoader getClassLoader();
				
//  AnnotatedTypeMetadata：
				boolean isAnnotated(String annotationType);
				Map<String, Object> getAnnotationAttributes(String annotationType);
				Map<String, Object> getAnnotationAttributes(String annotationType, boolean classValuesAsString);
				MultiValueMap(String, Object) getAllAnnotationAttributes(String annotationType);
				MultiValueMap(String, Object) getAllAnnotationAttributes(String annotationType, boolean classValuesAsString);