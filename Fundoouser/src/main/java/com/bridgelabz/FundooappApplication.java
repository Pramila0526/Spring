package com.bridgelabz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching(proxyTargetClass = true)
@SpringBootApplication
public class FundooappApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(FundooappApplication.class, args);
		
	}

}
