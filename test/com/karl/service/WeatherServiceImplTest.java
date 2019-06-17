package com.karl.service;

import org.junit.jupiter.api.Test;

class WeatherServiceImplTest {

	@Test
	void testGetWeather() {
		WeatherServiceImpl ws = new WeatherServiceImpl();
		ws.getWeather("π„÷›");
	}

}
