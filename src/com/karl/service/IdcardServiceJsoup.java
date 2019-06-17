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
		pw.print("���������֤��ַ" + BusinessSocketService.enter);
		pw.print("������q�������˵�" + BusinessSocketService.enter);
		pw.flush();
		String inputLine = scanner.nextLine();
		//�������q���������˵�
		if(inputLine.equalsIgnoreCase("q")) {
			BusinessSocketService.mainMenu(pw); 
			//����q֮���޷��ٴλ�ȡ�û���������֣�������Ҫ��ȡ
			String msg = scanner.nextLine();
			BusinessSocketService.dealUserInput(scanner, pw, msg);
			
		}
		//������֤�����Ƿ�Ϸ�
		boolean sign = CheckIdcardIsValid(inputLine);
		if(!sign) { 
			//�������false
			pw.println("����������֤���벻�Ϸ�������������");
			pw.flush();
			mainMethod(scanner, pw);
		}
		//����Ϸ�������һ��ҵ�񷽷�
		String content =getContent(inputLine);
		pw.println(content);
		pw.flush();
		mainMethod(scanner, pw);
	}
	
	//������֤�����Ƿ�Ϸ��ķ���
	public boolean CheckIdcardIsValid(String idcard) {
		boolean sign = true;
		if(idcard.length() != 18) {
			sign = false;
		}
		return sign;
	}
	
	/*
	 * ʹ��Jsoup��ȡ���֤��Ϣ
	 * @param idcardNumber
	 * 			���֤��
	 * @return ���֤���룺xxxxxxxxxx <br>
	 * 		        �Ա��� <br>
	 * 			���գ�20190101 <br>
	 *			 ��ַ���㶫ʡ����������� 
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
				idcard.setSex(elements.get(0).text());		//�Ա�
				idcard.setBirthday(elements.get(1).text()); //��������
				idcard.setAddress(elements.get(2).text());  //��ַ��Ϣ
			}
			content = idcard.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return content;
	}
}
