package com.karl.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import com.karl.model.Weather;

public class WeatherServiceImpl implements WeatherService {

	@Override
	public void mainMethod(Scanner scanner, PrintWriter pw) {
		pw.print("�������������" + BusinessSocketService.enter);
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
		
		// ����Ϸ�������һ��ҵ�񷽷�
		String content = getContent(inputLine);
		pw.println(content);
		pw.flush();
		mainMethod(scanner, pw);
	}
	
	//��ȡ��������Ԥ��
	public String getContent(String inputLine) {
		Weather weather = getWeather(inputLine);
		return weather.toString();
	}
	
	//���ݳ������ƻ�ȡ������Ϣ
	public String getContentFromURL(String city) {
		StringBuffer sb = new StringBuffer();
		
		String webUrl = "https://www.sojson.com/open/api/weather/json.shtml?city" + city;
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
	
	
	public Weather getWeather(String city) {
		Weather  weather = new Weather();
		String content = getContentFromURL(city);
		String date = getDate(content);
		weather.setDate(date);
		weather.setCity(city);
		weather.setHigh(getHigh(content));
		weather.setLow(getLow(content));
		weather.setType(getType(content));
		weather.setNotice(getNotice(content));
		
		return weather;
	}
	
	//��ȡ����
	public String getDate(String content) {
		int beginIndex = content.indexOf("forecast");
		int dateBeginIndex = content.indexOf("date", beginIndex);
		int dateEndIndex = content.indexOf("\",", beginIndex);
		String str = content.substring(dateBeginIndex+7, dateEndIndex);
		return str;
	}
	//��ȡ����¶�
	public String getHigh(String content) {
		int beginIndex = content.indexOf("forecast");
		int dateBeginIndex = content.indexOf("high", beginIndex);
		int dateEndIndex = content.indexOf("\",", beginIndex);
		String str = content.substring(dateBeginIndex+7, dateEndIndex);
		return str;
	}
	//��ȡ����¶�
	public String getLow(String content) {
		int beginIndex = content.indexOf("forecast");
		int dateBeginIndex = content.indexOf("low", beginIndex);
		int dateEndIndex = content.indexOf("\",", beginIndex);
		String str = content.substring(dateBeginIndex+6, dateEndIndex);
		return str;
	}
	//��ȡ����
	public String getType(String content) {
		int beginIndex = content.indexOf("forecast");
		int dateBeginIndex = content.indexOf("type", beginIndex);
		int dateEndIndex = content.indexOf("\",", beginIndex);
		String str = content.substring(dateBeginIndex+7, dateEndIndex);
		return str;
	}
	//��ȡע������
	public String getNotice(String content) {
		int beginIndex = content.indexOf("forecast");
		int dateBeginIndex = content.indexOf("notice", beginIndex);
		int dateEndIndex = content.indexOf("\"}", beginIndex);
		String str = content.substring(dateBeginIndex+9, dateEndIndex);
		return str;
	}
	 
}
