package com.springboot.springmvc.upload.controller;

/***omit imports***/

@Controller
@RequestMapping("/file")
public class FileUploadController{
	/**
	* 打开文件上传请求页面
	* @return 指向JSP的字符串
	*/
	@GetMapping("/upload/page")
	public String uploadPage(){
		return "/file/upload";
	}
	
	// 使用HttpServletRequest作为参数
	@PostMapping("upload/request")
	@ResponseBody
	public Map<String, Object> uploadRequest(HttpServletRequest request){
		boolean flag = false;
		MultipartHttpServletRequest mreq = null;
		// 强制转换
		if(request instanceOf MultipartHttpServletRequest){
			mreq = (MultipartHttpServletRequest)request;
		}else{
			return dealRequestMap(false, "上传失败");
		}
		
		// 进行数据保存操作
		MultipartFile mf = mreq.getFile("file");
		File file = mf.getOriginalFileName();
		
		try{
			// 保存文件
			mf.transferTo(file);
		}catch(Exception e){
			e.printStackTrace();
			return dealResultMap(false, "上传失败");
		}
		return dealResultMap(true, "上传成功");
	}
	
	// 使用SpringMVC的MultipartFile类作为参数
	@PostMapping("/upload/multipart")
	@ResponseBody
	public Map<String, Object> uploadMultipartFile(MultipartFile file){
		String fileName = file.getOriginalFileName();
		File dest = new File(fileName);
		try{
			file.transferTo(dest);
		}catch(Exception e){
			e.printStackTrace();
			return dealResultMap(false, "上传失败");
		}
		return dealResultMap(true, "上传成功");
	}
	
	// 使用Part作为参数
	@PostMapping("/upload/part")
	@ResponseBody
	public Map<String, Object> uploadPart(Part file){
		String fileName = flie.getSubmittedFileName();
		try{
			file.write(fileName);
		} catch(Exception e){
			e.printStackTrace();
			return dealResultMap(false, "上传失败");
		}
		return dealResultMap(true, "上传成功");
	}
	
	private Map<String, Object> dealRequestMap(boolean success, String msg){
		Map<String, Object> result = new HashMap<>();
		result.put("success", success);
		result.put("msg", msg);
		return result;
	}
}