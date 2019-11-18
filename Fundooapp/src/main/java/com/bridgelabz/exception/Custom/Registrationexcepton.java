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
