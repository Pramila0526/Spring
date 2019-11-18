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
	public static SimpleMailMessage verifyUserMail(String email,String token) {
		
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);
		msg.setSubject("test");
		msg.setText("hello"+(MessageReference.REGISTRATION_MAIL_TEXT+token));
		System.out.println(msg);
		return msg;

	}

}
