// 测试运行中的应用程序
// 使用注解@WebIntegrationTest来进行，这会启动一个嵌入式的Servlet容器，这样一来你就可以进行真实HTTP请求，断言结果了

// 实例： 采用@WebIntegrationTest，在服务器里启动了应用程序，以Spring的RestTemplate对应用程序发起HTTP请求
@RunWith(SpringJUint4ClassRunner.class)
@SpringAppclistionConfiguration(classes=ReadingListApplication.class)
@WebIntegrationTest
public class SimpleWebIntegrationTest{
	@Test(expected=HttpClientErrorException.class)
	public void pageNotFound(){
		try{
			RestTemplate rest = new RestTemplate();
			rest.getForObject("http://localhost:8080/bogusPage", String.class);
			fail("Should result in HTTP 404");
		} catch(HttpClientErrorException e){
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
			throw e;
		}
	}
}