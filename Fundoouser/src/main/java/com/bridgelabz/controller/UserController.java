
/******************************************************************************
 *  Compilation:  javac -d bin UserController.java
 *  Execution:   
 *               
 *  
 *  Purpose:    main purpose user controller class is   handle all operation
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/
package com.bridgelabz.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bridgelabz.dto.Logindto;
import com.bridgelabz.dto.Registerdto;
import com.bridgelabz.dto.Setpassworddto;
import com.bridgelabz.model.User;
import com.bridgelabz.response.Response;
import com.bridgelabz.services.MessageReference;
import com.bridgelabz.services.ServiceImp;
import org.springframework.web.multipart.*;

@RestController
@RequestMapping("/")
public class UserController {

	@Autowired
	private ServiceImp serviceimp;

	/**
	 * @param regdto registerdto store all user data in regdto
	 * @return return user response for if data add return successfully message
	 *         otherwise return not add
	 */
	@PostMapping("/addregister")
	public ResponseEntity<Response> addNewUser(@RequestBody Registerdto regDto) {

		return new ResponseEntity<Response>(serviceimp.addNewUser(regDto), HttpStatus.OK); // give response for user 200
																							// - ok
		// message for user
		// return final return
	}

	/**
	 * @param id user give for show all own information give service imp class
	 */
	@GetMapping("/finduser")
	public ResponseEntity<Response> findByuser(@RequestParam String id) {

		return new ResponseEntity<Response>(serviceimp.findByUser(id), HttpStatus.OK);
	}

	/**
	 * @return show all users information
	 */
	@GetMapping("/show")
	public Response Show() {

		List<?> list = serviceimp.Show();

		return new Response(200, "Show all details", list);
	}

	/**
	 * @param id Give id for who users want delete it
	 * @return if user is found delete successful response for user otherwise not
	 */
	@DeleteMapping("/delete")
	public ResponseEntity<Response> deleteUser(@RequestParam String id) {

		return new ResponseEntity<Response>(serviceimp.deleteUser(id), HttpStatus.OK);

	}

	/**
	 * @param user provide model class for getter or setter
	 * @param id   give id for who want update detail in list
	 * @return if update return successful or not
	 */
	@PutMapping("/updateuser")
	public ResponseEntity<Response>  updateuser(User user, @RequestParam String id) {

		
		
		return new ResponseEntity<Response>(serviceimp.updateuser(user, id), HttpStatus.OK);
		
	}

	/**
	 * @param logindto provide user getter setter for login
	 * @return if user user name & password is match return success or not
	 */
	@PostMapping("/login")
	public ResponseEntity<Response> loginUser(@RequestBody Logindto logindto, @RequestParam String token) {
		
		return new ResponseEntity<Response>(serviceimp.loginUser(logindto, token), HttpStatus.OK);


	}

	/**
	 * @param token server send response for user in token
	 * @return if server token & user token is match return success or not
	 */
	@PostMapping("/validate")
	public ResponseEntity<Response> validate(@RequestParam String token) {

		return new ResponseEntity<Response>(serviceimp.valivateUser(token), HttpStatus.OK);
	}

	/**
	 * @param email user provide email fort check it validate for not
	 * @return send the token for user email id
	 */
	@PostMapping("/forgotpassword/{email}")
	public String findEmail(@PathVariable String email) {
		System.out.println("in controller");
		serviceimp.findEmail(email);
		return MessageReference.MAIL_SEND;
	}

	/**
	 * @param token          generate user token for verify token it
	 * @param setpassworddto user give new password change it
	 * @return return set new password successfully or not
	 */
	@PostMapping("/verfiy")
	public ResponseEntity<Response> setNewPassword(@RequestParam String token,
			@RequestBody Setpassworddto setpassworddto) {

		return new ResponseEntity<Response>(serviceimp.setPassword(setpassworddto, token), HttpStatus.OK);
	}
	
	
	/**
	 * @param file      
	 * @param userid
	 * @return
	 * @throws IOException
	 */
	
	@PostMapping(value="/addprofile", consumes="multipart/form-data")
	public ResponseEntity<Response> addProfile(MultipartFile file,@RequestHeader String userid) throws IOException{
		return new ResponseEntity<Response>(serviceimp.addProfile(file, userid),HttpStatus.OK);
	}
	@DeleteMapping("/deleteprofile")
	public ResponseEntity<Response> deleteProfile(@RequestParam String  profileName,@RequestParam String userid ) {
         System.out.println("in controller");
		return new ResponseEntity<Response>(serviceimp.deleteProfile( profileName,userid), HttpStatus.OK);

	}
	@PutMapping("/getprofile")
	public ResponseEntity<Resource> getProfile(@RequestHeader String userid,HttpServletRequest request) throws IOException{
		
		return serviceimp.getProfile(userid,request);
	}

}
