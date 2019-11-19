/******************************************************************************
 *  Compilation:  javac -d bin Service.java
 *  Execution:    
 *               
 *  
 *  Purpose:       create   interface for  perform all operation
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/
package com.bridgelabz.services;



import java.util.List;
import java.util.Optional;

import com.bridgelabz.dto.Forgotdto;
import com.bridgelabz.dto.Logindto;
import com.bridgelabz.dto.Registerdto;
import com.bridgelabz.dto.Setpassworddto;
import com.bridgelabz.model.User;
import com.bridgelabz.response.Response;


public interface Service {
	
	
	
	public String  addNewUser(Registerdto regdto);	    //create addNewUser() method for add new user
	
	public  String loginUser(Logindto logindto);        //create loginUser() method for login user
	
	public Optional<User> findByUser( String id);          //create findByUser() method for check user detail present or not
	
	public List<User> Show();                           // create Show() method for display all user details
	
	public String deleteUser(String id);                   //create deleteUser() method for remove particular user
	
	public String updateuser(User user,  String id);       //create UpdateUser() method for update particular user
	 
	public Response findEmail(String email); //create findEmail() method for change particular user email
	
	
	public Response setPassword(Setpassworddto setPasswordDto,String token);
	
	                                                    //create setPassword() method for set  new password particular user email
	
	
	
	
	
	

}
