
/******************************************************************************
 *  Compilation:  javac -d bin Response.java
 *  Execution:    
 *               
 *  
 *  Purpose:       create response class for response to user 
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/
package com.bridgelabz.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class Response  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6597721492410014185L;
	private int status;          //create status integer for user response 
	private String message;     // create message in String for give the user message
	private Object data;        // create  data for give any information

	public Response(int status, String message, Object data) {    //create constructor
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

}
