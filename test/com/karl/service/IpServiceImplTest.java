package com.karl.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class IpServiceImplTest {

	@Test
	void testMainMethod() {
	}

	

	@Test
	void testGetContent() {
	}
	
	@Test
	void testGetSpNameFromContent() {
		IpServiceImpl service = new IpServiceImpl();
		String content = service.getContentFromURL("8.8.8.8");
		String spName = service.getSpNameFromContent(content);
		System.out.println(spName);
	}	
	
	@Test
	void testGetContentFromURL() {
		IpServiceImpl service = new IpServiceImpl();
		String content = service.getContentFromURL("192.168.20.111");
		System.out.println(content);
	}

	@Test
	void testCheckIpIsValid() {
		IpServiceImpl service = new IpServiceImpl();
		assertEquals(true,service.CheckIpIsValid("192.168.20.111")); 
	}
}
