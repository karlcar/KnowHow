package com.karl.main;

import com.karl.service.BusinessSocketService;

//����ҵ��,������
public class MainClass {
	public static void main(String[] args) {
		System.out.println("��������ͨƽ̨���˿ں��ǣ�8082");
		BusinessSocketService.startServer();
	} 
}
  