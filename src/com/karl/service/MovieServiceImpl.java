package com.karl.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.karl.model.Movie;

public class MovieServiceImpl implements MovieService {

	@Override
	public void mainMethod(Scanner scanner, PrintWriter pw) {
		pw.print("�������Ӱ����" + BusinessSocketService.enter);
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

	public String getContent(String movieName) {
		String content = "";
		List<Movie> list = this.getMovieList(movieName);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Movie movie = (Movie) iterator.next();
			content += movie.toString();
		}
		return content;
	}
	
	//ȡ�����ص�ַ���������
	public String getDownloadUrlFromWebUrl(String webUrl) {
		String content = "";
		try {
			Document doc = Jsoup.connect(webUrl).get();
			Elements elements = doc.getElementsByAttributeValue("bgcolor", "#fdfddf");
			if(elements.size() == 1) {
				Elements es = elements.get(0).getElementsByTag("a");
				content = es.get(0).attr("href");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	//���������ĵ�Ӱ���Ƶļ���
	public List<Movie> getMovieList(String name){
		List<Movie> movieList = new ArrayList<>();
		//������ҳ�����ƽ�β��keyword�����˱��룬������Ҫ���н���
		String url = "";
		try {
			url = "http://s.ygdy8.com/plus/so.php?typeid=1&keyword=" + URLEncoder.encode(name, "gb2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(url);
		
		try {
			Document doc = Jsoup.connect(url).get();
			Elements elements = doc.getElementsByAttributeValue("class", "co_content8");
			if(elements.size() == 1) {
				//Elements es = elements.get(0).getElementsByTag("a");������ã�����������������,
				Elements es = elements.get(0).getElementsByAttributeValueMatching("href", "/html");
				System.out.println(es.size());
				for(int i=0; i<es.size();i++) {
					Movie movie = new Movie();
					String href = es.get(i).attr("href");//
					System.out.println(href);//
					movie.setName(name);
					movie.setTitle(es.get(i).text());
					//������ҳ��ַ����ȡ��Ӱ�����ص�ַ
					String downloadUrl = this.getDownloadUrlFromWebUrl("http://s.ygdy8.com" + href);
					movie.setUrl(downloadUrl);
					movieList.add(movie);
				}
			}
			System.out.println(elements.size());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return movieList;
	}
}
