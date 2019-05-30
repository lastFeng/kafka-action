/**
*	Spring的基础集成测试样例
* @RunWith(SpringJUnit4ClassRunner.class) 开启Spring集成测试支持
* @ContextConfiguration 指定了如何加载应用程序上下文
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AddressBookConfiguration.class)
@SpringApplicationTest
public class AddressServiceTests{
	// 注入地址服务
	@Autowired
	private AddressService addressService;
	
	public void testService(){
		// 测试地址服务
		Address addr = addService.findByLastName("Sheman");
		assertEquals("P", addr.getFirstName());
		assertEquals("Sherman", addr.getLastName());
		assertEquals("42 Wallaby Way", addr.getAddressLine1());
		assertEquals("Sydney", addr.getCity());
		assertEquals("New South Wales", addr.getState());
		assertEquals("2000", addr.getPostCode());
	}
}