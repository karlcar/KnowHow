package com.karl.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//处理网络业务
public class BusinessSocketService {
	public static String enter = "\r\n";

	public static void startServer() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8082);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				PrintWriter pw = new PrintWriter(socket.getOutputStream());// 获取一段输出流
				mainMenu(pw);
				// 客户会输入对应的数字，我们拿到对应的数字
				Scanner scanner = new Scanner(socket.getInputStream());
				String Command = scanner.nextLine();
				dealUserInput(scanner, pw, Command);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	// 主界面
	public static void mainMenu(PrintWriter pw) {
		String content = "欢迎进入百事通平台" + enter;
		content += "1:输入1：进入查询ip功能" + enter;
		content += "2:输入2：进入身份证号码查询功能" + enter;
		content += "3:输入3：进入手机号码归属地功能" + enter;
		content += "4:输入4：进入电影地址下载功能" + enter;
		content += "5:输入5：进入天气查询功能" + enter;
		pw.print(content);
		pw.flush(); // 刷新操作之后，服务端信息给到客户端
	}

	// 处理用户输入
	public static void dealUserInput(Scanner scanner, PrintWriter pw, String Command) {
		switch (Command) {
		case "1":
			/*
			 * pw.print("欢迎进入查询ip功能"); pw.flush();
			 */
			// TODO,加上对应的实现类
			IpService ipService = new IpServiceImpl();
			ipService.mainMethod(scanner, pw);
			break;
		case "2":
			IdcardService idcardService = new IdcardServiceJsoup();
			idcardService.mainMethod(scanner, pw);
			break;
		case "3":
			MobilePhoneService mobileService = new MobilePhoneServiceImpl();
			mobileService.mainMethod(scanner, pw);
			break;
		case "4":
			MovieService movieService = new MovieServiceImpl();
			movieService.mainMethod(scanner, pw);
			break;
		case "5":
			WeatherService ws = new WeatherServiceImpl();
			ws.mainMethod(scanner, pw);
			break;

		default:
			mainMenu(pw);
			break;
		}
	}
}
