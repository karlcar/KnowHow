package com.karl.service;

import org.junit.jupiter.api.Test;

class IdcardServiceJsoupTest {

	@Test
	void testCheckIdcardIsValid() {
		
	}

	@Test
	void testGetContent() {
		IdcardServiceJsoup idcardService = new IdcardServiceJsoup();
		String content = idcardService.getContent("360822198305022623");
		System.out.println(content);
	}

}
