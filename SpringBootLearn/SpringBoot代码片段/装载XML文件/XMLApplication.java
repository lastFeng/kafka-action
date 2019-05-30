// Spring 通过应用上下文装载bean的定义
// 可以使用ClassPathXMLApplicationContext来装载Bean

public class XMLApplication{
	public static void main(String[] args){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/xxx.xml");
		Knight knight = context.getBean(Knight.class);
		knight.method();
		
		context.close();
	}
}