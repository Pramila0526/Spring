/******************************************************************************
 *  Compilation:  javac -d bin Tokenutility.java
 *  Execution:    
 *               
 *  
 *  Purpose:       create utility for jwt  response in  token
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since  19-11-2019
 *
 ******************************************************************************/
package com.bridgelabz.note.dto;


import lombok.Data;

@Data
public class Notedto {
	
	

	
	
	private String title;
	private String  description;
	private String color;
	private boolean pin;
	private boolean trash;
	private boolean archive;
	private boolean remider;
	

}
