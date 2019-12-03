/******************************************************************************
 *  Compilation:  javac -d bin Logindto.java
 *  Execution:   
 *               
 *  
 *  Purpose:    create  dto for login user username and password
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since  19-11-2019
 *
 ******************************************************************************/
package com.bridgelabz.dto;

import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Logindto {
	
	//@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\. [A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="mail id format wrong")
	private String email;
	
	private String password;

}
