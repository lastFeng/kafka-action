/**
	通过@ControllerAdvice，我们可以将控制器的全局配置放在同一个位置
	注解了@Controller的类的方法可以使用@ExceptionHandler、@InitBinder、@ModelAttribute
		1. @ExceptionHandler：用于全局处理控制器里的异常
		2. @InitBinder：用来设置WebDataBinder,WebDataBinder是用来自动绑定前台请求参数到Model中
		3. @ModelAttribute：本来的作用是绑定键值对到Model中
*/

// 定制ControllerAdivce

@ControllerAdvice
public class ExceptionHandlerAdvice(){
	@ExceptionHandler(value=Exception.class)
	public ModelAndView exception(Exception exception, WebRequest request){
		ModelAndView mv = new ModelAndView("error"); // error页面
		mv.addObject("errorMessage", exception.getMessgae());
		return mv;
	}
	
	
	// 将键值对添加到全局，所有注解的@RequestMapping的方法都可以获得此键值对
	@ModelAttribute
	public void addAttributes(Model model){
		model.addAttribute("msg", "额外信息");
	}
	
	// 定制WebDataBinder
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.setDisallowedFieds("id"); // 忽略request参数的id
	}
}