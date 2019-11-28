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
package com.bridgelabz.note.exception.custom;

public class Usernotfoundexception  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Usernotfoundexception(String message)
	{
		
		super(message);
	}

}
