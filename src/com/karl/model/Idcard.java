package com.karl.model;

import com.karl.service.BusinessSocketService;

//�洢���֤��Ϣjavabean����
//ֻ�����ԡ����췽����ֻ�������洢����
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
		content += "���֤�ţ�" + idcardNumber + BusinessSocketService.enter;
		content += "�Ա�" + sex + BusinessSocketService.enter;
		content += "�������ڣ�" + birthday + BusinessSocketService.enter;
		content += "��ϸ��ַ��" + address + BusinessSocketService.enter;
		return content;
	}
	
}
