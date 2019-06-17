package com.karl.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import com.karl.thread.ClientThreadSocketInputStream;

public class Client {
	public static void main(String[] args) {
		System.out.println("客户端已经启动");
		try {
			Socket socket = new Socket("localhost",8082);
			//服务端会先发送一段一段话，需要先打印出来
			/*
			 * Scanner serverScanner = new Scanner(socket.getInputStream()); String welcome
			 * = serverScanner.nextLine(); System.out.println(welcome);
			 */
			//由于上方代码因为换行会只读到一行，要读多行就要运用以下的多线程的方法
			ClientThreadSocketInputStream thread = new ClientThreadSocketInputStream(socket);
			thread.start();
			
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			//监听键盘的输入
			Scanner s = new Scanner(System.in);
			while(s.hasNextLine()) {
				String msg = s.nextLine(); 
				pw.println(msg);  //客户端东西发送给服务端
				pw.flush();
				//等待服务器响应，如果不响应，我们阻塞在这里
				/*
				 * String line = serverScanner.nextLine(); System.out.println(line);
				 */  
			}
		}  catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
