package com.springboot.redis.controller.redistemplate;

public class RedisTemplateController{
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/{id}")
	public static UserVo getUser(Long id){
		// 设置url
		String url = "http://localhost:8080/user/{id}";
		// 通过RedisTemplate来获取，第一个参数为url，第二个参数为：返回类型，第三个参数为URI路径参数（即，需要URL中的自定义传入的参数）
		UserVo userVo = redisTempleate.getForObject(url, UserVo.class, id);
		
		System.out.println(userVo.getUserName());
		return userVo;
	}
	
	public static List<UserVo> findUser(String userName, String note, int start, int limit){
		Map<String, Object> params = new HashMap<>();
		params.put("note", note)
		params.put("userName", userName);
		params.put("start", start);
		params.put("limit", limit);
		String url = "http://localhost:8080/users/{userName}/{note}/{start}/{limit}";
		ResponseEntiy<List> responseEntity = restTemplate.getForEntity(url, List.class, params);
		List<UserVo> userVoes = responseEntity.getBody();
		return userVoes;
	}
	
	public static User insertUser(UserVo newUserVo){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<UserVo> request = new HttpEntity<>(newUserVo, headers);
		User user = redisTemplate.postForObject("http://localhost:8080/user", request, User.class);
		
		System.out.println(user.getId());
		
		return user;
	}
	
	// 执行DELETE请求
	public static void deleteUser(Long id){
		restTemplate.delelte("http://localhost:8080/user/{id}", id);
	}
	
	// 获取服务器响应头属性和HTTP状态码
	public User insertUserEntity(UserVo userVo){
		// 请求头
		HttpHeaders headers = new HttpHeaders();
		// 请求头类型
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		// 绑定请求体和请求头
		HttpEntity<UserVo> request = new HttpEntity<>(userVo, headers);
		
		// url
		String url = "http://localhost:8080/user/entity";
		
		// 请求服务器
		ResposeEntity<User> response = restTemplate.postForEntity(url, request, User.class);
		
		// 获取响应体
		User user = response.getBody();
		
		// 获取响应头
		HttpHeaders respHeader = response.getHeaders();
		// 获取响应属性
		List<String> success = respHeader.get("success");
		
		// 响应HTTP状态码
		int status = response.getStatusCodeValue();
		System.out.println(user.getId());
		return user;
	}
	
	// RedisTemplate的exchange()方法，作为资源的交换,灵活性较高
	public static User useExchange(UserVo userVo, Long id){
		// 请求头
		HttpHeaders header = new HttpHeaders();
		// 请求类型
		header.setContent(MediaType.APPLICATION_JSON_UTF8);
		// 绑定请求头和类型
		HttpEntity<UserVo> request = new HttpEntity<>(userVo, header);
		String url = "http://localhost:8080/user/entity"
		// 请求服务器
		ResponseEntity<User> response = redisTemplate.exchange(url, HttpMethod.POST, request, User.class);
		// 获取响应头
		HttpHeaders respHeader = response.getHeaders();
		// 获取响应体
		User user = response.getBody();
		// 响应头属性
		List<String> success = respHeader.get("success");
		// 响应的HTTP状态码
		int status = response.getStatusCodeValue();
		// 修改URL资源
		url = "http://localhost:8080/user/{id}";
		
		ResponseEntity<UserVo> userVoEntity = restTemplate.exchange(url, HttpMethod.GET, null, UserVo.class, id);
		// 获取响应体
		UserVo userVoe = userVoEntity.getBody();
		
		System.out.println(userVoe.getUserName());
		
		return user;
	}
}