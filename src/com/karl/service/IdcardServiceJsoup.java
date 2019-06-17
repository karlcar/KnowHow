package com.karl.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.karl.model.Idcard;

public class IdcardServiceJsoup implements IdcardService {

	@Override
	public void mainMethod(Scanner scanner, PrintWriter pw) {
		pw.print("请输入身份证地址" + BusinessSocketService.enter);
		pw.print("请输入q返回主菜单" + BusinessSocketService.enter);
		pw.flush();
		String inputLine = scanner.nextLine();
		//如果输入q，返回主菜单
		if(inputLine.equalsIgnoreCase("q")) {
			BusinessSocketService.mainMenu(pw); 
			//输入q之后无法再次获取用户输入的数字，所以需要获取
			String msg = scanner.nextLine();
			BusinessSocketService.dealUserInput(scanner, pw, msg);
			
		}
		//检查身份证号码是否合法
		boolean sign = CheckIdcardIsValid(inputLine);
		if(!sign) { 
			//如果返回false
			pw.println("您输入的身份证号码不合法，请重新输入");
			pw.flush();
			mainMethod(scanner, pw);
		}
		//如果合法，请用一个业务方法
		String content =getContent(inputLine);
		pw.println(content);
		pw.flush();
		mainMethod(scanner, pw);
	}
	
	//检查身份证号码是否合法的方法
	public boolean CheckIdcardIsValid(String idcard) {
		boolean sign = true;
		if(idcard.length() != 18) {
			sign = false;
		}
		return sign;
	}
	
	/*
	 * 使用Jsoup获取身份证信息
	 * @param idcardNumber
	 * 			身份证号
	 * @return 身份证号码：xxxxxxxxxx <br>
	 * 		        性别：男 <br>
	 * 			生日：20190101 <br>
	 *			 地址：广东省广州市天河区 
	 */ 
	public String getContent(String idcardNumber) {
		Idcard idcard = new Idcard();
		idcard.setIdcardNumber(idcardNumber);
		String content = "";
		String url = "http://qq.ip138.com/idsearch/index.asp?userid=" + idcardNumber + "&action=idcard&B1=%B2%E9+%D1%AF";
		try {
			Document doc = Jsoup.connect(url).get();
			Elements elements = doc.getElementsByAttributeValue("class","tdc2");
			if(elements.size() == 3) {
				idcard.setSex(elements.get(0).text());		//性别
				idcard.setBirthday(elements.get(1).text()); //出生日期
				idcard.setAddress(elements.get(2).text());  //地址信息
			}
			content = idcard.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return content;
	}
}
