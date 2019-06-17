package com.karl.model;

import com.karl.service.BusinessSocketService;

public class Movie {
	private String name;
	private String title;
	private String url;
	public Movie(String name, String title, String url) {
		super();
		this.name = name;
		this.title = title;
		this.url = url;
	}
	public Movie() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		String content = "";
		content += "��Ӱ����" + name + BusinessSocketService.enter;
		content += "����" + title + BusinessSocketService.enter;
		content += "���ص�ַ" + url + BusinessSocketService.enter;
		 
		return content;
	}
}
