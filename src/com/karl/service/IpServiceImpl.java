package com.karl.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class IpServiceImpl implements IpService {
	//主方法：第一个：ip地址查询的主方法，进入这个方法可以跟客户端进行交互，可以接受客户端输入，可以向客户端输出东西 
	
	@Override
	public void mainMethod(Scanner scanner, PrintWriter pw) {
		pw.print("请输入IP地址" + BusinessSocketService.enter);
		pw.print("请输入q返回主菜单" + BusinessSocketService.enter);
		pw.flush();
		String ip = scanner.nextLine();
		//如果输入q，返回主菜单
		if(ip.equalsIgnoreCase("q")) {
			BusinessSocketService.mainMenu(pw);
			//输入q之后无法再次获取用户输入的数字，所以需要获取
			String msg = scanner.nextLine();
			BusinessSocketService.dealUserInput(scanner, pw, msg);
			
		}
		//检查ip地址是否合法
		boolean sign = CheckIpIsValid(ip);
		if(!sign) {
			//如果返回false
			pw.println("您输入的IP地址不合法，请重新输入");
			pw.flush();
			mainMethod(scanner, pw);
		}
		//如果合法，请用一个业务方法
		String content = getContent(scanner, pw, ip);
		pw.println(content);
		pw.flush();
		mainMethod(scanner, pw);
		
	}
	
	//检查ip地址是否合法
	public boolean CheckIpIsValid(String ip) {
		boolean sign = true;
		//ip地址规则：有4段，3个小数点，每段最大255
		if(ip.length()>15) {
			sign = false; //3*4加上3个小数点，就是15个
		}
		String[] ipArray = ip.split("\\.");
		if(ipArray.length != 4) {
			sign = false; //ip分隔之后如果不是4段就不行 
		}
		
		for(int i=0; i<ipArray.length; i++) {
			Integer num = 0;
			try {
				num = Integer.parseInt(ipArray[i]);
			}catch(Exception e) {
				sign = false;
			}
			
			if(num>255 || num<0) {
				sign = false;
			}
			
		}
		return sign;
	}
	
	
	//获取ip地址
	@Override
	public String getContent(Scanner scanner, PrintWriter pw ,String ip) {
		//发起一个网络连接，获取网页的内容
		String content = getContentFromURL(ip);
		String spName = getSpNameFromContent(content); 
		//从网页里取出运营商
		return spName;
	}
	
	//根据IP地址获取运营商名称
	public String getContentFromURL(String ip) {
		StringBuffer sb = new StringBuffer();
		
		//http://www.ip138.com/ips138.asp?ip=120.230.101.12&action=2
		String webUrl = "http://www.ip138.com/ips138.asp?ip=" + ip +"&action=2";
		try {
			URL url = new URL(webUrl);
			URLConnection conn = url.openConnection();
			Scanner s = new Scanner(conn.getInputStream());
			
			while(s.hasNextLine()) {
				sb.append(s.nextLine()).append("\r\n");
			}
			s.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	//已经知道ip地址的位置，从网页当中取出运营商名称
	public String getSpNameFromContent(String content) {
 		String spName = "";
		if(content.contains("本站数据：") && content.contains("</li>")) {
			int beginIndex = content.indexOf("本站数据：");
			int endIndex = content.indexOf("</li>");
			spName = content.substring(beginIndex+5,endIndex);
		}
		return spName;
	}
	
	
}
