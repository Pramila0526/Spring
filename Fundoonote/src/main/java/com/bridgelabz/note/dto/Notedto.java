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

@Data   //genrate setter getter automically
public class Notedto {
	
	

	
	
	private String title;          //  getter setter of note title
	private String  description;   // getter setter of note  description
	private String color;          // getter setter of note color
	private boolean pin;           // getter setter of pin 
	private boolean trash;         // getter setter of trash 
	private boolean archive;       // getter setter of archive 
	private boolean remider;       // getter setter of remider 
	

}
