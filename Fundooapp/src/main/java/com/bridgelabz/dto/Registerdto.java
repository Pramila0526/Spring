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

import lombok.Data;

//

@Data
public class Registerdto {
	
	
	
	
	private String name;
	private String address;
	private String email;
	private String password;
	private boolean validate;
	

}
