package com.bridgelabz.note.response;

import lombok.Data;

@Data
public class Response {
	
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
