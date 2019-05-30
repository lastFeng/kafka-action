package com.springboot.security.service.impl;

@Service
pubic class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRoleService userRoleService;
	
	@Override
	@Transactiona
	public UserDetails loadUserByUserName(String userNmae) throws UsernameNotFoundException{
		// 获取数据库用户信息
		DatabaseUser dbUser = userRoleService.getUserByUserName(userName);
		
		List<DatabaseRole> roleList = userRoleService.findRolesByUserName(userName);
		// 将信息转换为UserDetails
		return changeToUser(dbUser, roleList);
	}
	
	private UserDetails changeToUser(DatabaseUser dbUser, List<DatabaseRole> roleList){
		// 权限列表
		List<GrantedAuthority> authorityList = new ArrayList<>()
		// 赋予查询到的角色
		for(DatabaseRole: roleList){
			GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
			authorityList.add(authority);
		}
		// 创建UserDetails对象，设置用户名、密码和权限
		UserDetails userDetails = new User(dbUser.getUserName(), dbUser.getPwd(), authorityList);
		return userDetails;
	}
}