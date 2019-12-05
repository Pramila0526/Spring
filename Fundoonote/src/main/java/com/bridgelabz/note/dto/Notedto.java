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


import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data   //genrate setter getter automically
public class Notedto {
	
	

	
	@NotBlank(message = "title is empty")	
	@Size(min = 3,max = 50,message = "Title should be statring  3 character")
	private String title;          //  getter setter of note title
	@NotBlank(message = "description is empty")	
	@Size(min = 3,max = 1000,message = "description should be statring  3 character")
	private String  description;   // getter setter of note  description
	@NotBlank(message = "color is empty")	
	private String color;          // getter setter of note color
	private boolean pin;           // getter setter of pin 
	private boolean trash;         // getter setter of trash 
	private boolean archive;       // getter setter of archive 
	private boolean remider;       // getter setter of remider 
	LocalDateTime datetime = LocalDateTime.now();
	

}
