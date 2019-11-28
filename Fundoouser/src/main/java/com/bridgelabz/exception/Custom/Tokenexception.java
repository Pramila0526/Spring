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
package com.bridgelabz.exception.Custom;

public class Tokenexception  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Tokenexception(String message)
	{
		
		super(message);
	}

}
