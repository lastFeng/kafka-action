package com.springboot.example.controller;

/***控制器***/
@RestController
@RequestMapping("/example")
public class PurchaseController{
	@Autowired
	private PurchaseService purchaseService;
	
	// 定义JSP视图
	@GetMapping("/test")
	public ModelAndView testPage(){
		ModelAndView mv = new ModelAndView("test");
		return mv;
	}
	
	@PostMapping("/purchase")
	public Result purchase(Long userId, Long productId, Integer quantity){
		boolean success = purchaseService.purchase(userId, productId, quantity);
		String message = success ? "抢购成功" : "抢购失败，库存不足";
		Result result = new Resutl(success, message);
		return result;
	}
	
	
	
	class Result{
		private boolean success;
		private String message;
		
		public Result(){
		}
		
		public Result(boolean success, String message){
			this.success = success;
			this.message = message;
		}
		/***setter and getter***/ 
	}
}