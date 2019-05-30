package com.springboot.redis.cache.service.impl;

/***omit imports***/
/**
* 通过缓存注解进行Redis缓存设置
**/

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

		// 查询单个用户
	@Override
	@Cacheable(value="redisCache", key="'redis_user_' + #id")
	public User getUserById(Long id){
		return userDao.getUserById(id);
	}
	
	// 新增
	@Override
	@CachePut(value="redisCache", key="'redis_user_' + #result.id")
	public User insertUser(User user){
		userDao.insertUser(user);
		return user;
	}
	
	// 修改
	@Override
	@CachePut(value="redisCache", condition="#result != 'null'", key="'redis_user_' + #id")
	public User updateUserName(Long id, String userName){
		User user = this.getUserById(id);
		if(user==null){
			return null;
		}
		user.setUserName(userName);
		userDao.updateUser
		return user;
	}
	
	// 查询
	@Override
	List<User> findUserListByUserNameAndnote(String userName, String note){
		return userDao.findUserListByUserNameAndnote(userName, note);
	}
	
	// 删除
	@Override
	@CacheEvict(value="redisCache", key="'redis_user_' + #id", beforeInvocation=false)
	int deleteUserById(Long id){
		return userDao.deleteUserById(id);
	}
}