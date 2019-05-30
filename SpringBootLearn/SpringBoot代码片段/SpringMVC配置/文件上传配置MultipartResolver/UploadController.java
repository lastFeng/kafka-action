// SpringMVC通过配置一个MultipartResolver来上传文件
// 添加依赖
// 		commons-fileupload
//		commons-io   ----非必要

// #############上传页面 upload.jsp ####################
<% @page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transactional//EN" "http://w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<title>upload page</title>
	</head>
	
	<body>
		<form action="upload" enctype="multipart/form-data" method="post">
			<input type="file" name="file"/> <br/>
			<input type="sumbit" value="上传"/>
		</form>
	</body>
</html>


// #################ViewController#####################WebMvcConfigurerAdapter
@Override
public void addViewControllers(ViewControllerRegistry registry){
	registry.addViewController("/index").setViewName("/index");
	registry.addViewController("/upload").setViewName("/upload");
}

// #########################MultipartResolver配置##################################
@Bean
public MultipartResolver multipartResolver(){
	CommonMultipartResolver multipartResolver = new CommonMultipartResolver();
	multipartResolver.setMaxUploadSize(1000000);
	return multipartResolver;
}


// ###############################控制器##############################################
@RestController
public class UploadController{
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(MultipartFile file){
		try{
			FileUtils.writeByteArrayToFile(new File("xxx" + file.getOriginalFilename()), file.getByte());
			return "ok";
		}
		catch(IOException e){
			e.printStackTrace();
			retrun "wrong";
		}
	}
}
