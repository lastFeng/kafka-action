package com.springboot.webflux.service.impl;

public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Mono<User> getUser(Long id){
		return userRepository.findById(id);
	}
	
	@Override
	public Mono<User> insertUser(User user){
		return userRepository.save(user);
	}
	
	@Override
	public Mono<User> updateUser(User user){
		return userRepository.save(user);
	}
	
	@Override
	public Mono<Void> deleteUser(Long id){
		Mono<Void> result = userRepository.deleteById(id);
		return result;
	}
	
	@Overrid
	public Flux<User> findUsers(String userName, String note){
		return userRepository.findByUserNameLikeAndNoteLike(userName, note);
	}
}