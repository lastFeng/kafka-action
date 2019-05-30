// Spring Security测试
// 依赖：	spring-security-test

/*
* 运行之前，创建MockMvc实例时，运行Spring Security的配置器
**/



@RunWith(SpringJUint4ClassRunner.class)
@SpringApplicationConfiguration(classes=xxx.class)
@WebAppConfiguration
public class MockMvcSecurityTest{
	
	@Autowired
	private WebApplicationContext webContext;
	
	private MockMvc mockMvc;
	
	@Before
	public void setupMockMvc{
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext)
														 .apply(SecurityMockMvcConfigurers.springSecurity())   // springSecurity() 是SecurityMockMvcConfigurers的一个静态方法，
														 .build();
	}
	
	@Test
	public void homePage_unauthenticatedUser() throws Exception{
		mockMvc.perform("/")
					 .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
					 .andExpect(MockMvcResultMatchers.header().string("Location", "http://localhost/login"));
	}
	
	
	/**
	* 经过验证的Spring Security提供了两个注解
	*		@WithMockUser：			加载安全上下文，其中包含一个UserDetails，使用了给定的用户名、密码和授权；
	*   @WithUserDetails：  根据给定的用户名查找UserDetails对象，加载安全上下文
	*/
	@Test
	@WithMockUser(username="craig",
								password="password",
								role="READER")
	public void homePage_authenticatedUser() throws Exception{
		...
	}
	
	@Test
	@WithUserDetails("craig")				// craig用户
	public void homePage_authenticatedUser() throws Exception{
		Reader expectedReader = new Reader();			// 期望的Reader
		expectedReader.setUsername("craig");
		expectedReader.setPassword("password");
		expectedReader.setFullname("Craig Walls");
		
		mockMvc.perform(MockMvcRequestBuilders.get("/"))					// 发起GET请求
					 .andExpect(MockMvcResutltMatchers.status().isOK())
					 .andExpect(MockMvcResutltMatchers.view().name("readingList"))
					 .andExpect(MockMvcResutltMatchers.model.attribute("reader", Mathcers.samePropertyValuesAs(expectedReader)))
					 .andExpect(MockMvcResutltMatchers.model.attribute("books", Matchers.hasSize(0)));
	}
}