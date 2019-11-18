package com.bridgelabz.model;

public class Message {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void showmessage()
	{
		System.out.println("Message is : "+message);
	}

}
