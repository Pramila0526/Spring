package com.bridgelabz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Passwordconfig   {

	
	
	/**
	 * @return   it  return incrypt user  password  and store it database 
	 */
	@Bean
	public PasswordEncoder encoder()
	{
		
		return new BCryptPasswordEncoder();
	}

}
