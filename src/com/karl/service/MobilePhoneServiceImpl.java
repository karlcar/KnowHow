package com.karl.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.karl.model.Mobile;

public class MobilePhoneServiceImpl implements MobilePhoneService {

	@Override
	public void mainMethod(Scanner scanner, PrintWriter pw) {
		pw.print("请输入手机号码" + BusinessSocketService.enter);
		pw.print("请输入q返回主菜单" + BusinessSocketService.enter);
		pw.flush();
		String inputLine = scanner.nextLine();
		// 如果输入q，返回主菜单
		if (inputLine.equalsIgnoreCase("q")) {
			BusinessSocketService.mainMenu(pw);
			// 输入q之后无法再次获取用户输入的数字，所以需要获取
			String msg = scanner.nextLine();
			BusinessSocketService.dealUserInput(scanner, pw, msg);

		}
		// 检查手机号码是否合法
		boolean sign = CheckMobileIsValid(inputLine);
		if (!sign) {
			// 如果返回false
			pw.println("您输入的手机号码不合法，请重新输入");
			pw.flush();
			mainMethod(scanner, pw);
		}
		// 如果合法，请用一个业务方法
		String content = getContent(inputLine);
		pw.println(content);
		pw.flush();
		mainMethod(scanner, pw);
	}

	public boolean CheckMobileIsValid(String mobile) {
		boolean sign = true;
		if (mobile.length() != 11) {
			sign = false;
		}
		// 正常用正则表达式判断手机号码数字，这里用for循环
		String[] numArray = mobile.split("");
		for (int i = 0; i < numArray.length; i++) {
			try {
				Integer.parseInt(numArray[i]);
			} catch (Exception e) {
				return false;
			}
		}

		if (!mobile.startsWith("1")) {
			sign = false;
		}
		return sign;
	}

	public String getContent(String mobile) {
		String content = "";
		String url = "http://www.ip138.com:8080/search.asp?mobile=" + mobile + "&action=mobile";
		Mobile mobileObject = new Mobile(); 
		mobileObject.setPhoneNumber(mobile);
		try {
			Document doc = Jsoup.connect(url).get();
			Elements elements = doc.getElementsByAttributeValue("class", "tdc2");
			if (elements.size() > 2) {
				String address = elements.get(1).text();
				String brand = elements.get(2).text();
				mobileObject.setAddress(address);
				mobileObject.setBrand(brand);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		content = mobileObject.toString();
		return content;
	}

}
