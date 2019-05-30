package com.springboot.redis.controller;

@Controller
@RequestMapping("/redis")
public class RedisController{
	@Autowired
	private RedisTemplate redisTemplate = null;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate = null;
	
	@RequestMapping("/stringAndHash")
	@ResponseBody
	public Map<String, Object> testStringAndHash(){
		redisTemplate.opsForValue.set("key1", "value1");
		
		// 注意这里使用了JDK的序列化器，所以Redis保存时不是整数，不能运算
		redisTemplate.opsForValue.set("int_key", "1");
		stringRedisTemplate.opsForValue.set("int", "1");
		// 使用运算
		stringRedisTemplate.opsForValue.increment("int", "1");
		
		// 获取底层Jedis连接
		Jedis jedis = (Jedis)redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
		
		jedis.decr("int");
		
		Map<String, String> hash = new HashMap<>();
		hash.put("field1", "value1");
		hash.put("field2", "value2");
		
		// 存入一个散列数据类型
		stringRedisTemplate.opsForHash().putAll("hash", hash);
		
		// 新增一个字段
		stringRedisTemplate.opsForHash().put("hash", "field3", "value3");
		
		// 绑定散列操作的key，这也可以连续对同一个散列数据类型进行操作
		BoundHashOperations hashOps = stringRedisTemplate.boundHashOps("hash");
		
		// 删除两个字段
		hashOps.delete("field1", "field2");
		
	  // 新增一个字段
	  hashOps.put("field4", "value4");
	  
	  Map<String, Object> map = new HashMap<>();
	  map.put("success", true);
	  return map;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> testList(){
		
		// 从左边将所有元素
		stringRedisTemplate.opsForList().leftPushAll("list1", "1", "2");
		stringRedisTemplate.opsForList().rightPushAll("list2", "1", "2");
		
		BoundListOperations listOps = stringRedisTemplate.boundListOps("list1");
		
		Long size = listOps.size();
		
		List elements = listOps.range(0, size-2);
		
		Map<String, Object> map = new HashMap<>();
		map.put("success", true);
		return map;
	}
}