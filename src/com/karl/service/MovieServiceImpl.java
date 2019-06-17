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
		pw.print("请输入电影名称" + BusinessSocketService.enter);
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

	public String getContent(String movieName) {
		String content = "";
		List<Movie> list = this.getMovieList(movieName);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Movie movie = (Movie) iterator.next();
			content += movie.toString();
		}
		return content;
	}
	
	//取出下载地址里面的文字
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
	
	//返回搜索的电影名称的集合
	public List<Movie> getMovieList(String name){
		List<Movie> movieList = new ArrayList<>();
		//由于网页将名称结尾的keyword进行了编码，我们需要进行解析
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
				//Elements es = elements.get(0).getElementsByTag("a");这个不好，会有其他两个链接,
				Elements es = elements.get(0).getElementsByAttributeValueMatching("href", "/html");
				System.out.println(es.size());
				for(int i=0; i<es.size();i++) {
					Movie movie = new Movie();
					String href = es.get(i).attr("href");//
					System.out.println(href);//
					movie.setName(name);
					movie.setTitle(es.get(i).text());
					//根据网页地址，获取电影的下载地址
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
