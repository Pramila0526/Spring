
/******************************************************************************
 *  Compilation:  javac -d bin modelmapper.java
 *  Execution:    
 *               
 *  
 *  Purpose:       main purpose this class create for configuration modelmapper
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/

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
