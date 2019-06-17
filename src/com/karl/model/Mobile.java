package com.karl.model;

import com.karl.service.BusinessSocketService;

public class Mobile {
	private String phoneNumber;
	private String address;
	private String brand;
	
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Mobile(String phoneNumber, String address, String brand) {
		super();
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.brand = brand;
	}
	

	public Mobile() {
		super();
	}

	@Override
	public String toString() {
		String content = "";
		content += "�ֻ����룺" + phoneNumber + BusinessSocketService.enter;  
		content += "�ֻ������أ�" + address + BusinessSocketService.enter;  
		content += "Ʒ�ƣ�" + brand + BusinessSocketService.enter;  
		return content;
	}
	
}
