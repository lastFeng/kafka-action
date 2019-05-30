package com.springboot.redis.transaction;

/***omit imports***/

/***这里需要注意：RedisTemplate的键和散列结构的field使用字符串序列化器（StringRedisSerializer）***/
public class RedisTransactionController{
	@Autowired
	RedisTemplate redisTemplate = null;
	
	@RequestMapping("/multi")
	@ResponseBody
	public Map<String, Object> testMulti(){
		redisTemplate.opsForValue().set("key1", "value1");
		
		// 通常命令组合是： watch...multi...exec...
		// 其中：
		//   watch：监控Redis的键
		//   multi：开始事务，但不会马上执行，将其存放在一个队列中
		//   exec： 检测Redis的键值对是否发生了改变，要么全部执行，要么全部取消
		
		// 利用Lambda表达式进行设置
		List list = (List) redisTemplate.execute((RedisOperations operations) -> {
						// 设置需要监控的key1
						operations.watch("key1");
						
						// 开启事务，在exec命令执行前，全部都只是进入队列
						operations.multi();
						operations.opsForValue().set("key2", "value2");
						
						// operations.opsForValue().increment("key1", 1);
						
						// 获取值对为null，因为redis只是把命令放入队列中，并不执行命令，所以没有任何返回值，这里容易出错
						// 也就是这里的value2的值应该是为null，而不是“value2”
						Object value2 = operatios.opsForValue().get("key2");						
						System.out.println("命令在队列，所有value的值为： " + value2 + " 而不是预想中的value2。");
						
						operations.opsForValue().set("key3", "value3");
						System.out.println("命令在队列，所有value的值为：" + value3 + " 而不是预想中的value3。");
						
						// 执行exec命令，将先判断key1是否在监控后被修改过，如果是，则不执行事务；否则就执行事务
						return operations.exec();
				});
				
		System.out.println(list);
		Map<String, Object> map = new HashMap<>();
		map.put("success", true);
		return map;
	}
}