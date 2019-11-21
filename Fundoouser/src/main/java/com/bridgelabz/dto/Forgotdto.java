/******************************************************************************
 *  Compilation:  javac -d bin Forgotdto.java
 *  Execution:   
 *               
 *  
 *  Purpose:    create dto for forgot password
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/
package com.bridgelabz.dto;

import lombok.Data;

@Data
public class Forgotdto {    // create forgot dto for forgot password
	
	private String email;

}
