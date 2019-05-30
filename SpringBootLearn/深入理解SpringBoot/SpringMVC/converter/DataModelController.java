package com.springboot.springmvc.model.controller;

/***omit imports***/

@Controller
@RequestMapping("/data")
public class DataModelController{
	// 注入用户服务类
	@Autowired
	private UserService userService;
	
	// 测试model借口
	@GetMapping("/model")
	public String userModel(Long id, Model model){
		User user = userService.getUserById(id);
		model.setAttribute("user", user);
		
		// 这里返回字符串，在SpingMvc中，会自动创建ModelAndView且绑定名称
		return "data/user";
	}
	
	// 测试ModelMap
	@GetMapping("/modelMap")
	public ModelAndView useModelMap(Long id, ModelMap modelMap){
		User user = userService.getUserById(id);
		ModelAndVies mv = new ModelAndView();
		
		mv.setViewName("data/user");
		
		modelMap.put("user", user);
		// 不需要将ModelMap手动设置到ModelAndView中，会自动设置
		return mv;
	}
	
	// 测试ModelAndView
	@GetMapping("/mav")
	public ModelAndView useModeAndView(Long id, ModelAndView mv){
		User user = userService.getUserById(id);
		mv.addObject("user", user);
		
		return mv;
	}
}