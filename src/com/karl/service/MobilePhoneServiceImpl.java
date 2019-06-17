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
		pw.print("�������ֻ�����" + BusinessSocketService.enter);
		pw.print("������q�������˵�" + BusinessSocketService.enter);
		pw.flush();
		String inputLine = scanner.nextLine();
		// �������q���������˵�
		if (inputLine.equalsIgnoreCase("q")) {
			BusinessSocketService.mainMenu(pw);
			// ����q֮���޷��ٴλ�ȡ�û���������֣�������Ҫ��ȡ
			String msg = scanner.nextLine();
			BusinessSocketService.dealUserInput(scanner, pw, msg);

		}
		// ����ֻ������Ƿ�Ϸ�
		boolean sign = CheckMobileIsValid(inputLine);
		if (!sign) {
			// �������false
			pw.println("��������ֻ����벻�Ϸ�������������");
			pw.flush();
			mainMethod(scanner, pw);
		}
		// ����Ϸ�������һ��ҵ�񷽷�
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
		// ������������ʽ�ж��ֻ��������֣�������forѭ��
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
