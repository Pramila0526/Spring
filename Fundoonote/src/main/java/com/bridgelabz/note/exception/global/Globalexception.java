package com.bridgelabz.note.exception.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.note.exception.custom.Labelnotfoundexception;
import com.bridgelabz.note.exception.custom.Notenotfoundexception;
import com.bridgelabz.note.exception.custom.Tokenexception;
import com.bridgelabz.note.exception.custom.Usernotfoundexception;
import com.bridgelabz.note.response.Response;


@ControllerAdvice
public class Globalexception {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> exception(Exception e){
		
		return new ResponseEntity<Response>(new Response(401,"internal server error",null),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Labelnotfoundexception.class)
	public ResponseEntity<Response> labelNotFoundException(Exception e){
		
		return new ResponseEntity<Response>(new Response(401, e.getMessage(), null),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Notenotfoundexception.class)
	public ResponseEntity<Response> noteNotFoundException1(Exception e){
		
		return new ResponseEntity<Response>(new Response(401, e.getMessage(), null),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Tokenexception.class)
	public ResponseEntity<Response> tokenException(Exception e){
		
		return new ResponseEntity<Response>(new Response(401, e.getMessage(), null),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(Usernotfoundexception.class)
	public ResponseEntity<Response> usernotfoundException(Exception e){
		
		return new ResponseEntity<Response>(new Response(401, e.getMessage(), null),HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
