package com.karl.service;

import java.io.PrintWriter;
import java.util.Scanner;

public interface IpService {
	//第一个：ip地址查询的主方法，进入这个方法可以跟客户端进行交互，可以接受客户端输入，可以向客户端输出东西
	public void mainMethod(Scanner scanner,PrintWriter pw);
	public String getContent(Scanner scanner,PrintWriter pw,String ip);
}
