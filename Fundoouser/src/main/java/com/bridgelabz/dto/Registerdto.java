/******************************************************************************
 *  Compilation:  javac -d bin Registerdto.java
 *  Execution:   
 *               
 *  
 *  Purpose:    create dto for register new user
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/
package com.bridgelabz.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

//

@Data
public class Registerdto {
	
	
	
	
	@NotBlank(message = "First Name is mandatory")	
	@Pattern(regexp = "^[a-zA-Z]*$")
	private String firstname;
	
	
	@NotBlank(message = "Last Name is mandatory")	
	@Pattern(regexp = "^[a-zA-Z]*$")
	private String lastname;
	
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\. [A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="phone format wrong" )
	private String email;

	@NotBlank(message = "password is mandatory")
	private String password;
	
	@Pattern(regexp = "(0/91)?[7-9][0-9]{9}", message="phone format wrong" )
	@NotBlank(message = "phone is mandatory")
	private long phonenumber;
	
	
	

}
