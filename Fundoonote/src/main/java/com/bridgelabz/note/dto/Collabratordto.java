/******************************************************************************
 *  Compilation:  javac -d bin Tokenutility.java
 *  Execution:    
 *               
 *  
 *  Purpose:       create  collaborator dto for collaborator other user
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since  25-11-2019
 *
 ******************************************************************************/
package com.bridgelabz.note.dto;

import lombok.Data;

@Data
public class Collabratordto {
	
	private String ownerId;    //user note id
	
	private String colaboratorId; //other user id
	

}
