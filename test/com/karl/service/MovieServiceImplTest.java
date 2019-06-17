package com.karl.service;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class MovieServiceImplTest {

	@Test
	void testGetContent() {
		fail("Not yet implemented");
	}

	@Test
	void testGetDownloadUrlFromWebUrl() {
		MovieServiceImpl ms = new MovieServiceImpl();
		String movieUrl = "https://www.ygdy8.com/html/gndy/dyzz/20171201/55673.html";
		System.out.println(ms.getDownloadUrlFromWebUrl(movieUrl));
	}
	
	@Test
	void testGetMovieList() {
		MovieServiceImpl ms = new MovieServiceImpl();
		ms.getMovieList("Õ½ÀÇ");
	}
}
