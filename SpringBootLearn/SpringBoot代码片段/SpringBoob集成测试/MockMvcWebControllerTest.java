// Web Controller Test
/**
* Web Controller 示例

	@RequestMapping(method=RequestMethod.POST)
	public String addToReadingList(Book book){
		book.setReader(reader);
		readingListRepository.save(book);
		return "redirect:/readingList";
	}

*/
/**
	  SpringMockMVC: 能在一个近似真实的模拟Servlet容器里测试控制器，而不用实际启动应用服务器（测试时使用）
	  Web集成测试：在嵌入式Servlet容器（比如Tomcat或Jetty）里启动应用程序，在真正的应用服务器里执行测试
*/

/**
		在测试时设置Mock MVC，可以使用MockMVCBuilders，该类提供了两个静态方法
			1. standaloneSetup()：构建一个Mock MVC，提供一个或多个手工创建并配置的控制器-------手工初始化并注入要测试的控制器
			2. webAPPContextSetup()：使用Spring应用程序上下文来构建Mock MVC，该上下文里可以包含一个或多个配置好的控制器-------基于一个WebApplicationContext的实例，由Spring加载
*/

import static org.hamcrest.Matchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=ReadlingListApplication.class)
@WebAppConfiguration   // 开启Web上下文测试
public class MockMvcWebControllerTest{
	@Autowired
	private  WebApplicationContext webContext;
	
	private MockMvc mockMvc;
	
	// 设置MockMvc，在测试执行前执行
	@Before
	public void setupMockMvc(){
		mockMvc = MockMvcBuilders
							.webAppContextSetup(webContext)
							.build();
	}
	
	/**
		向/readlingList发送一个HTTP GET请求，判断模型和视图是否满足我们的期望
	*/
	@Test
	public void homePage() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/readlingList")) 	//	 获取/readingList页面的Get请求
					 .andExpect(MockMvcResultMatchers.status().isOK()) 	// 查看获取的状态
					 .andExpect(MockMvcResultMatchers.view().name("readlingList"))		// 查看获取的视图名称是否是“readlingList”
					 .andExpect(MockMvcResultMatchers.model().attributeExists("books"))		// 查看获取模型中是否存在属性“books”
					 .andExpect(MockMvcResultMatchers.model().attribute("books", Matchers.is(Matchers.empty())))	// 查看获取的books属性是否为空 
	}
	
	/**
			HTTP POST 测试
	*/
	@Test
	public void postBook() throws Exception{
		mockMvc.perform(MockMvcRequstBuilders.post("/readingList")
					 .contentType(MediaType.APPLICATION_FORM_URLENCODED)
					 .param("title", "BOOK TITLE")
					 .param("author", "BOOK AUTHOR")
					 .param("isbn", "1234567890")
					 .param("description", "DESCRIPTION"))
					 .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
					 .andExpect(MockMvcResultMatchers.header().string("Location", "readingList"));
		
		Book expectBook = new Book();
		expectBook.setId(1L);
		expectBook.setReader("craig");
		expectBook.setTitle("BOOK TITLE");
		expectBook.setAuthor("BOOK AUTHOR");
		expectBook.setIsbn("1234567890");
		expectBook.setDescription("DESCRIPTION");
		
		mockMvc.perform(MockMvcRequestBuilders.get("/readlingList"))
					 .andExpect(MockMvcResultMatchers.status().isOK())
					 .andExpect(MockMvcResultMatchers.view().name("readlingList")
					 .andExpect(MockMvcResultMatchers.model.attributeExists("books"))
					 .andExpect(MockMvcResultMatchers.model.attribute("books", Matchers.contains(Matchers.samePropertyValuesAs(expectBook))));
	}
	
}