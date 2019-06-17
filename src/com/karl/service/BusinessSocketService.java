package com.karl.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//��������ҵ��
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
				PrintWriter pw = new PrintWriter(socket.getOutputStream());// ��ȡһ�������
				mainMenu(pw);
				// �ͻ��������Ӧ�����֣������õ���Ӧ������
				Scanner scanner = new Scanner(socket.getInputStream());
				String Command = scanner.nextLine();
				dealUserInput(scanner, pw, Command);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	// ������
	public static void mainMenu(PrintWriter pw) {
		String content = "��ӭ�������ͨƽ̨" + enter;
		content += "1:����1�������ѯip����" + enter;
		content += "2:����2���������֤�����ѯ����" + enter;
		content += "3:����3�������ֻ���������ع���" + enter;
		content += "4:����4�������Ӱ��ַ���ع���" + enter;
		content += "5:����5������������ѯ����" + enter;
		pw.print(content);
		pw.flush(); // ˢ�²���֮�󣬷������Ϣ�����ͻ���
	}

	// �����û�����
	public static void dealUserInput(Scanner scanner, PrintWriter pw, String Command) {
		switch (Command) {
		case "1":
			/*
			 * pw.print("��ӭ�����ѯip����"); pw.flush();
			 */
			// TODO,���϶�Ӧ��ʵ����
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
