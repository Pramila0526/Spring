package com.bridgelabz.note;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;

@SpringBootApplication
@Cacheable
public class FundoonoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(FundoonoteApplication.class, args);
		
	}

}
