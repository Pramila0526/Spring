/******************************************************************************
 *  Compilation:  javac -d bin Responseutility.java
 *  Execution:    
 *               
 *  
 *  Purpose:       create utility for  Rabbitmq
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since  19-11-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.utility;

import java.util.List;

import com.bridgelabz.note.model.Notemodel;
import com.bridgelabz.note.response.Response;

public class Responseutility {
	
	
	
	
	public static Response customSuccessResponse(String message) {
		
		return new Response(200, message, true);
	}
	
    public static Response customUnsuccessResponse(String message) {
		
		return new Response(200, message, true);
	}
    
    public static <T> Response customSucessResponse(String message,List<T> list) {
    	
    	return new Response(200, message, list);
    }
	public static  Response CustomSucessResponse(String message,Notemodel note) {
		
		return new Response(200,message, note);
	}

}
