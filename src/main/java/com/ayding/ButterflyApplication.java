package com.ayding;

import com.ayding.system.init.ConfigApplicationContextInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ButterflyApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(ButterflyApplication.class);
		springApplication.addInitializers(new ConfigApplicationContextInitializer());
		springApplication.run(args);
	}

}
