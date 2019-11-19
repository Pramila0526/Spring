/******************************************************************************
 *  Compilation:  javac -d bin Setpassworddto.java
 *  Execution:   
 *               
 *  
 *  Purpose:    create dto for  set new password of user
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/
package com.bridgelabz.dto;

import lombok.Data;

@Data
public class Setpassworddto {
	

	private String password;
	private String cfmpassword;
	

}
