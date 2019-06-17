package com.karl.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class IpServiceImpl implements IpService {
	//����������һ����ip��ַ��ѯ������������������������Ը��ͻ��˽��н��������Խ��ܿͻ������룬������ͻ���������� 
	
	@Override
	public void mainMethod(Scanner scanner, PrintWriter pw) {
		pw.print("������IP��ַ" + BusinessSocketService.enter);
		pw.print("������q�������˵�" + BusinessSocketService.enter);
		pw.flush();
		String ip = scanner.nextLine();
		//�������q���������˵�
		if(ip.equalsIgnoreCase("q")) {
			BusinessSocketService.mainMenu(pw);
			//����q֮���޷��ٴλ�ȡ�û���������֣�������Ҫ��ȡ
			String msg = scanner.nextLine();
			BusinessSocketService.dealUserInput(scanner, pw, msg);
			
		}
		//���ip��ַ�Ƿ�Ϸ�
		boolean sign = CheckIpIsValid(ip);
		if(!sign) {
			//�������false
			pw.println("�������IP��ַ���Ϸ�������������");
			pw.flush();
			mainMethod(scanner, pw);
		}
		//����Ϸ�������һ��ҵ�񷽷�
		String content = getContent(scanner, pw, ip);
		pw.println(content);
		pw.flush();
		mainMethod(scanner, pw);
		
	}
	
	//���ip��ַ�Ƿ�Ϸ�
	public boolean CheckIpIsValid(String ip) {
		boolean sign = true;
		//ip��ַ������4�Σ�3��С���㣬ÿ�����255
		if(ip.length()>15) {
			sign = false; //3*4����3��С���㣬����15��
		}
		String[] ipArray = ip.split("\\.");
		if(ipArray.length != 4) {
			sign = false; //ip�ָ�֮���������4�ξͲ��� 
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
	
	
	//��ȡip��ַ
	@Override
	public String getContent(Scanner scanner, PrintWriter pw ,String ip) {
		//����һ���������ӣ���ȡ��ҳ������
		String content = getContentFromURL(ip);
		String spName = getSpNameFromContent(content); 
		//����ҳ��ȡ����Ӫ��
		return spName;
	}
	
	//����IP��ַ��ȡ��Ӫ������
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
	
	//�Ѿ�֪��ip��ַ��λ�ã�����ҳ����ȡ����Ӫ������
	public String getSpNameFromContent(String content) {
 		String spName = "";
		if(content.contains("��վ���ݣ�") && content.contains("</li>")) {
			int beginIndex = content.indexOf("��վ���ݣ�");
			int endIndex = content.indexOf("</li>");
			spName = content.substring(beginIndex+5,endIndex);
		}
		return spName;
	}
	
	
}
