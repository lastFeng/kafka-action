package com.springboot.async.controller;

@Controller
@RequestMapping("/async")
public class AsyncController{
	@Autowired
	private AsyncService asyncService;
	
	@GetMapping("/page")
	public String asyncPage(){
		System.out.println("请求线程名称：" + "【" + Thread.currentThread().getName() + "】");
		// 调用异步服务
		asyncService.generateReport();
		return "async";
	}
}