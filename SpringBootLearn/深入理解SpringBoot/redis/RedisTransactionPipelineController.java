package com.springboot.redis.transaction;

/***omit imports***/

@Controller
/***使用Redis流水线进行批量处理，提升redis性能***/ 
public class RedisTransactionPipelineController{
	@Autowired
	private RedisTemplate redisTemplate = null;
	
	@RequestMapping("/pipeline")
	@ResponseBody
	public Map<String, Object> testPipeline(){
	
		// 	
		Long start = System.currentTimeMillis();
		
		List list = (List) redisTemplate.executePipeline(RedisOperations operations -> {
			for(int i = 1; i <= 100000; i++){
				operations.opsForValue().set("pipeline_" + i, "value_" + i);
				String value = operation.opsForValue().get("pipeline_" + i);
				if(i == 100000){
					System.out.println("命令只是进入了队列，没有真正执行，所以值为：" + value + " 而不是预想的value_" + i);
				}
			}
			return null;
		});
		
		Long end = System.currentTimeMillis();
		
		System.out.println("耗时：" + (end - start) + "ms");
		
		Map<String, Object> map = new HashMap<>();
		map.put("success", true);
		return map;
	}
}