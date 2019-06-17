package com.karl.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import com.karl.thread.ClientThreadSocketInputStream;

public class Client {
	public static void main(String[] args) {
		System.out.println("�ͻ����Ѿ�����");
		try {
			Socket socket = new Socket("localhost",8082);
			//����˻��ȷ���һ��һ�λ�����Ҫ�ȴ�ӡ����
			/*
			 * Scanner serverScanner = new Scanner(socket.getInputStream()); String welcome
			 * = serverScanner.nextLine(); System.out.println(welcome);
			 */
			//�����Ϸ�������Ϊ���л�ֻ����һ�У�Ҫ�����о�Ҫ�������µĶ��̵߳ķ���
			ClientThreadSocketInputStream thread = new ClientThreadSocketInputStream(socket);
			thread.start();
			
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			//�������̵�����
			Scanner s = new Scanner(System.in);
			while(s.hasNextLine()) {
				String msg = s.nextLine(); 
				pw.println(msg);  //�ͻ��˶������͸������
				pw.flush();
				//�ȴ���������Ӧ���������Ӧ����������������
				/*
				 * String line = serverScanner.nextLine(); System.out.println(line);
				 */  
			}
		}  catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
