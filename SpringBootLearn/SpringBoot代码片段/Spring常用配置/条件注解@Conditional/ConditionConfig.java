// 条件注解：@Conditional
// @Conditional 根据满足某一个特定条件创建一个特定的Bean。

// 1. 实现Condition接口的matches方法，来进行判定条件的满足，如果返回true，则是匹配的
// 2. 配置相应的@Bean，同时利用@Conditional来指定满足条件，才会实例化相应的Bean

// 1. 判定条件定义

// a. 判定Windows的条件
class WindowsCondition implements Condition{
	@Override
	public boolean matches(ConditionContext context, AnnotationTypeMetadate metadate){
		return context.getEnvironment().getProperty("os.name").contains("Windows");
	}
}

// b. 判定Linux的条件
class LinuxCondition implments Condition{
	@Override
	public boolean matches(ConditionContext context, AnnotationTypeMetadate metadate){
		return context.getEnvironment().getProperty("os.name").contains("Linux");
	}
}

// 2. 不同系统下的Bean类
// a. 接口
interface ListService{
	public String showListCmd();
}

// Windows下所要创建的Bean类
class WindowsListService implements ListService{
	@Override
	public String showListCmd(){
		return "dir";
	}
}

// Linux下所要创建的Bean类
class LinuxListService implements ListService{
	@Override
	public String showListCmd(){
		return "ls";
	}
}

// 配置类
@Configuration
public class ConditionConfig{
	@Bean
	@Conditional(WindowsCondition.class)
	public ListService windowsListService(){
		return new WindowsListService();
	}
	
	@Bean
	@Conditional(LinuxCondition.class)
	public ListService linuxListService(){
		return new LinuxListService();
	}
}