package com.karl.service;

import java.io.PrintWriter;
import java.util.Scanner;

public interface IpService {
	//��һ����ip��ַ��ѯ������������������������Ը��ͻ��˽��н��������Խ��ܿͻ������룬������ͻ����������
	public void mainMethod(Scanner scanner,PrintWriter pw);
	public String getContent(Scanner scanner,PrintWriter pw,String ip);
}
