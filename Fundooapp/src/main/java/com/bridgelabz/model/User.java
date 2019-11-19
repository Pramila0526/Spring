
/******************************************************************************
 *  Compilation:  javac -d bin modelmapper.java
 *  Execution:    
 *               
 *  
 *  Purpose:       create user model using  lombok
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since  19-11-2019
 *
 ******************************************************************************/
package com.bridgelabz.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

//generate automatically   getter  setter  using lombok

@Document
@Data
public class User {
	
	
	@Id
	private String id;
	
	@NotBlank(message = "Name is mandatory")
	private String name;
	@NotBlank(message = "address is mandatory")
	private String address;
	@NotBlank(message = "email is mandatory")
	private String email;
	@NotBlank(message = "password is mandatory")
	private String password;
	
	private boolean validate;
	
	 
}
