// 使用Selenium来模拟手动获取网址
// 依赖：selenium-java

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=ReadingListApplication.class)
@WebIntegrationTest(radomPort=true)   // 开启随机端口号
public class ServerWebTest{
	private Static FirefoxDriver browser;   // 火狐浏览器驱动
	
	@Value("${local.server.port}")				// 注入端口号
	private int port;
	
	@BeforeClass
	public static void openBrowser(){		// 会创建FirefoxDriver实例
		browser = new FirefoxDriver();
		browser.manage().timeouts()
					 .implicitlyWait(10, TimeUnit.SECONDS);		// 设置Firefox驱动
	}
	
	@AfterClass
	public static void closeBrowser(){
		browser.quit();			// 关闭浏览器
	}
	
	@Test
	public void addBookToEmptyList(){
		String baseUrl = "http://localhost:" + port;
		
		browser.get(baseUrl);
		
		// 判断图书列表是否为空
		assertEquals("You have no books in your book list", browser.findElementByTagName("div").getText());
		
		// 填充并发送表单
		browser.findElementByName("title").sendKeys("BOOK TITLE");
		browser.findElementByName("author").snedKeys("BOOK AUTHOR");
		browser.findElementByName("isbn").sendKeys("1234567890");
		browser.findElementByName("description").sendKeys("DESCRIPTION");
		browser.findElementByName("form").submit();
		
		WebElement dl = browser.findElementByCssSelector("dt.bookHeadline");
		assertEquals("BOOK TITLE by BOOK AUTHOR (ISN: 1234567890)", dl.getText());
		
		WebElement dt = browser.findElementByCssSelector("dd.bookDescription");
		assertEquals("DESCRIPTION", dt.getText());
	}
}