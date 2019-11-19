/******************************************************************************
 *  Compilation:  javac -d bin Response.java
 *  Execution:    
 *               
 *  
 *  Purpose:       create  utility for  simple mail sending 
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since  19-11-2019
 *
 ******************************************************************************/
package com.bridgelabz.utility;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.bridgelabz.services.MessageReference;

@Component
public class Utility {

	/**
	 * Purpose:this method can be create object of simple mail message and return it
	 * 
	 * @param email user give email for checking
	 * @return simple email message object
	 */
	public static SimpleMailMessage verifyUserMail(String email,String token,String link) {
		
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);     //send mail
		msg.setSubject("test"); //send message for user email account
		msg.setText("hello"+(link+token));  //send token for  user email  account
	
		return msg;

	}

}
