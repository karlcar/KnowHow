package com.karl.main;

import com.karl.service.BusinessSocketService;

//启动业务,主方法
public class MainClass {
	public static void main(String[] args) {
		System.out.println("启动百事通平台，端口号是：8082");
		BusinessSocketService.startServer();
	} 
}
  