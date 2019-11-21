
/******************************************************************************
 *  Compilation:  javac -d bin Passwordconfig.java
 *  Execution:   
 *               
 *  
 *  Purpose:     Main purpose this class   configuration user password
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since  19-11-2019
 *
 ******************************************************************************/
package com.bridgelabz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration          //configuration annotation
public class Passwordconfig   {

	
	
	/**
	 * @return   it  return encrypt user  password  and store it database 
	 */
	@Bean
	public PasswordEncoder encoder()
	{
		
		return new BCryptPasswordEncoder();
	}
	

	
	
}
