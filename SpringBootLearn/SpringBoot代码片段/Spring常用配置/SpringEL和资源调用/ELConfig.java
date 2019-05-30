// Dependency ---- commons-io
// properties classpath: com/wisely/hightlight_spring4/el/test.properties
//				book.author=wangyunfei
//				book.name=spring boot


@Configuration
@ComponentScan("com.wisely.hightlight_spring4.el") // 扫描位置
@PropertySource("classpath:com/wisely/hightlight_spring4/el/test.properties")  // 注入指定的配置文件
//@ConfigurationProperties(prefix="book")   // 指定注入配置文件中，内容的前缀，之后的的配置文件中的名称与属性名一致即可注入
public class ELConfig{
	
	// 注入普通字符
	@Value("I Love You!")
	private String normalString;
	
	// 注入操作系统属性
	@Value("#{systemProperties['os.name']}")
	private String osName;
	
	// 注入表达式结果, 可以利用函数进行注入
	@Value("#{T(java.lang.Math).random() * 100.0}")
	private double randomNumber;
	
	// 注入其他Bean属性
	@Value("#{demoService.another}")
	private String fromAnother;
	
	// 注入指定文件资源，该文件中存储任意内容
	@Value("classpath:com/wisely/hightligth_spring4/el/test.txt")
	private Resource testFile;
	
	// 注入网址资源
	@Value("http://www.baidu.com")
	private Resource testUrl;
	
	// 注入配置文件
	@Value("${book.name}")
	private String bookName;
	
}