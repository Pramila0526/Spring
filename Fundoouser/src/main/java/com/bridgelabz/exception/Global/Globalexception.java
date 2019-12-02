package com.bridgelabz.exception.Global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.exception.Custom.Forgotpasswordexception;
import com.bridgelabz.exception.Custom.Loginexception;
import com.bridgelabz.exception.Custom.Registrationexcepton;
import com.bridgelabz.exception.Custom.Tokenexception;
import com.bridgelabz.exception.Custom.Validateuserexception;
import com.bridgelabz.exception.Custom.Validationexception;
import com.bridgelabz.response.Response;

@ControllerAdvice
public class Globalexception {
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<Response> exception(Exception e) {
//
//		return new ResponseEntity<Response>(new Response(401, "internal server error", null),
//				HttpStatus.INTERNAL_SERVER_ERROR);
//	}

	@ExceptionHandler(Loginexception.class)
	public ResponseEntity<Response> loginException(Exception e) {

		return new ResponseEntity<Response>(new Response(401, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Registrationexcepton.class)
	public ResponseEntity<Response> registrationExcepton(Exception e) {

		return new ResponseEntity<Response>(new Response(401, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Tokenexception.class)
	public ResponseEntity<Response> tokenException(Exception e) {

		return new ResponseEntity<Response>(new Response(401, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Validationexception.class)
	public ResponseEntity<Response> validationException(Exception e) {

		return new ResponseEntity<Response>(new Response(401, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Validateuserexception.class)
	public ResponseEntity<Response> ValidateUserException(Exception e) {

		return new ResponseEntity<Response>(new Response(401, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Forgotpasswordexception.class)
	public ResponseEntity<Response> ForgotPasswordException(Exception e) {

		return new ResponseEntity<Response>(new Response(401, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
