package com.karl.model;

import com.karl.service.BusinessSocketService;

//存储身份证信息javabean对象
//只有属性、构造方法，只能用来存储数据
public class Idcard {
	private String idcardNumber;
	private String sex;
	private String birthday;
	private String address;
	public Idcard(String idcardNumber, String sex, String birthday, String address) {
		super();
		this.idcardNumber = idcardNumber;
		this.sex = sex;
		this.birthday = birthday;
		this.address = address;
	}
	public Idcard() {
		super();
	}
	public String getIdcardNumber() {
		return idcardNumber;
	}
	public void setIdcardNumber(String idcardNumber) {
		this.idcardNumber = idcardNumber;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		String content = "";
		content += "身份证号：" + idcardNumber + BusinessSocketService.enter;
		content += "性别：" + sex + BusinessSocketService.enter;
		content += "出生日期：" + birthday + BusinessSocketService.enter;
		content += "详细地址：" + address + BusinessSocketService.enter;
		return content;
	}
	
}
