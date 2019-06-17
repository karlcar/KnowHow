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
		pw.print("请输入城市名称" + BusinessSocketService.enter);
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
		
		// 如果合法，请用一个业务方法
		String content = getContent(inputLine);
		pw.println(content);
		pw.flush();
		mainMethod(scanner, pw);
	}
	
	//获取当天天气预报
	public String getContent(String inputLine) {
		Weather weather = getWeather(inputLine);
		return weather.toString();
	}
	
	//根据城市名称获取天气信息
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
	
	//获取日期
	public String getDate(String content) {
		int beginIndex = content.indexOf("forecast");
		int dateBeginIndex = content.indexOf("date", beginIndex);
		int dateEndIndex = content.indexOf("\",", beginIndex);
		String str = content.substring(dateBeginIndex+7, dateEndIndex);
		return str;
	}
	//获取最高温度
	public String getHigh(String content) {
		int beginIndex = content.indexOf("forecast");
		int dateBeginIndex = content.indexOf("high", beginIndex);
		int dateEndIndex = content.indexOf("\",", beginIndex);
		String str = content.substring(dateBeginIndex+7, dateEndIndex);
		return str;
	}
	//获取最低温度
	public String getLow(String content) {
		int beginIndex = content.indexOf("forecast");
		int dateBeginIndex = content.indexOf("low", beginIndex);
		int dateEndIndex = content.indexOf("\",", beginIndex);
		String str = content.substring(dateBeginIndex+6, dateEndIndex);
		return str;
	}
	//获取天气
	public String getType(String content) {
		int beginIndex = content.indexOf("forecast");
		int dateBeginIndex = content.indexOf("type", beginIndex);
		int dateEndIndex = content.indexOf("\",", beginIndex);
		String str = content.substring(dateBeginIndex+7, dateEndIndex);
		return str;
	}
	//获取注意事项
	public String getNotice(String content) {
		int beginIndex = content.indexOf("forecast");
		int dateBeginIndex = content.indexOf("notice", beginIndex);
		int dateEndIndex = content.indexOf("\"}", beginIndex);
		String str = content.substring(dateBeginIndex+9, dateEndIndex);
		return str;
	}
	 
}
