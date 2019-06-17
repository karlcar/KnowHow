package com.karl.demo;



import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class FastJsonDemo {
	//fastjson 
	static class Person{
		String name;
		String sex;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
	}
	//1:�Ѷ���ת��json�ַ���
	public  void test1() {
		Person p = new Person();
		p.setName("zejia");
		p.setSex("��");
		
		String json = JSON.toJSONString(p);
		System.out.println(json);
	}
	
	//�Ѽ��ϱ���ַ���
	public  void test2() {
		Person p1 = new Person();
		p1.setName("zejia");
		p1.setSex("��");
		Person p2 = new Person();
		p2.setName("fanbingbing");
		p2.setSex("Ů");
		
		List<Person> list = new ArrayList<>();
		list.add(p1);
		list.add(p2);
		
		String json = JSON.toJSONString(list);
		System.out.println(json);
	}
	
	//��json�ַ�����ɶ���
	public void test3() {
		String content = "{\"name\":\"zejia\",\"sex\":\"��\"}";
		Person p = (Person)JSON.parseObject(content, Person.class);
		System.out.println(p.getName());
		p.getSex();
		System.out.println(p.getSex());
	}
	
	//��json���ϱ�ɶ���[{"name":"zejia","sex":"��"},{"name":"fanbingbing","sex":"Ů"}]
	/*
	 * public void test4() { String content =
	 * "[{\"name\":\"zejia\",\"sex\":\"��\"},{\"name\":\"fanbingbing\",\"sex\":\"Ů\"}]";
	 * List<Person> list = JSON.parseObject(content, List.class); for (Iterator
	 * iterator = list.iterator(); iterator.hasNext();) { Person person = (Person)
	 * iterator.next(); System.out.println(person.name); } }
	 */
	
	//jsonObject
	public void test5() {
		String content = "{\"name\":\"zejia\",\"sex\":\"��\"}";
		JSONObject json = JSON.parseObject(content);
		System.out.println(json.get("name"));
		System.out.println(json.get("sex"));
	}
	
	public static void main(String[] args) {
		FastJsonDemo fs = new FastJsonDemo();
		fs.test5();
	}
}
