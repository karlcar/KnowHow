package com.karl.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class MobilePhoneServiceImplTest {

	@Test
	void testMainMethod() {
		fail("Not yet implemented");
	}

	@Test
	void testCheckMobileIsValid() {
		MobilePhoneServiceImpl ms = new MobilePhoneServiceImpl();
		assertEquals(false, ms.CheckMobileIsValid("232fddf323"));
	}

	@Test
	void testGetContent() {
		MobilePhoneServiceImpl ms = new MobilePhoneServiceImpl();
		System.out.println(ms.getContent("15814808439")); 
	}

}
