package com.bridgelabz.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	/**
	 * @return return model mapper object
	 */
	@Bean
	public ModelMapper getMapper() {
		
		return new ModelMapper();
	}
	
	
}
