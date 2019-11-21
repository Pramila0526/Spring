/******************************************************************************
 *  Compilation:  javac -d bin Registrationexcepton.java
 *  Execution:   
 *               
 *  
 *  Purpose:    create custom for  registration user
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/
package com.bridgelabz.exception.Custom;

public class Registrationexcepton extends RuntimeException {

	/**
	 *     Throws new exception for give wrong information
	 */
	private static final long serialVersionUID = 1L;
	
	public Registrationexcepton(String message)
	{
		super(message);
	}
	

}
