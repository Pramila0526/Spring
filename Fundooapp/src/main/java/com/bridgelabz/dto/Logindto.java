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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Logindto {
	
	private String email;
	private String password;

}
