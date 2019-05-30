package com.springboot.async.service.impl;

@Service
public class AsyncServiceImpl implements AsyncService{
	@Override
	@Async
	public void generateReport(){
		// 打印异步线程名称
		System.out.println("报表线程名称：" + "【" + Thread.currentThread().getName() + "】");
	}
}