package com.ayding.butterfly;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Base64;

@SpringBootTest
class ButterflyApplicationTests {

	@Test
	void contextLoads() {
		String ss = "1ySTdyV6o02AVWriKwQBh0WKLYoa/Kzp5djdeV6+fGzIIFVowov7tHeEVt4NNjfpvbpHK5plpmYsq63Q25Zz5Q==";
		String encode = Base64.getEncoder().encodeToString(ss.getBytes());
		System.err.println("======================encode:"+encode);
	}

}
