
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

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

//generate automatically   getter  setter  using lombok

@Document
@Data
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3062820861321260332L;
	@Id
	private String id;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private long phonenumber;
	private boolean validate;
	private String profile;

}
