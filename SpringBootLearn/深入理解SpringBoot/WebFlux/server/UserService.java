package com.springboot.webflux.service;

// Mono存放0~1个数据流
// Flux存放0~N个数据流
public interface UserService{
	Mono<User> getUser(Long id);
	Mono<User> insertUser(User user);
	Mono<User> updateUser<User user>;
	Mono<Void> deleteUser(Long id);
	Flux<User> findUsers(String userName, String note);
}