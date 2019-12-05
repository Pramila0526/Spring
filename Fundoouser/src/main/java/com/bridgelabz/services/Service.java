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



import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import com.bridgelabz.dto.Logindto;
import com.bridgelabz.dto.Registerdto;
import com.bridgelabz.dto.Setpassworddto;
import com.bridgelabz.model.User;
import com.bridgelabz.response.Response;


public interface Service {
	
	
	
	public Response  addNewUser(Registerdto regdto);	    //create addNewUser() method for add new user
	
	public  Response loginUser(Logindto logindto);        //create loginUser() method for login user
	
	public Response findByUser(String token);          //create findByUser() method for check user detail present or not
	
	public List<User> Show(String token);                           // create Show() method for display all user details
	
	public Response deleteUser(String token);                   //create deleteUser() method for remove particular user
	
	public Response updateuser(User user,String token);       //create UpdateUser() method for update particular user
	 
	public Response findEmail(String email ,String token); //create findEmail() method for change particular user email
	
	
	public Response setPassword(Setpassworddto setPasswordDto,String token);   //create setPassword() method for set  new password particular user email
	public Response addProfile(MultipartFile file,String token) throws IOException;
	public Response deleteProfile(String  profileName,String token);
	public ResponseEntity<Resource> getProfile( String userid,HttpServletRequest request ) throws MalformedURLException, IOException; 
	
	
	                                                  
	
	
	
	
	
	

}
