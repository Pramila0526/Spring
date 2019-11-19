/******************************************************************************
 *  Compilation:  javac -d bin Response.java
 *  Execution:    
 *               
 *  
 *  Purpose:       create  service implemention class and implement all service  interface method
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/
package com.bridgelabz.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.bridgelabz.config.Passwordconfig;
import com.bridgelabz.dto.Logindto;
import com.bridgelabz.dto.Registerdto;
import com.bridgelabz.dto.Setpassworddto;
import com.bridgelabz.exception.Custom.Registrationexcepton;
import com.bridgelabz.model.User;
import com.bridgelabz.repo.Userrepo;
import com.bridgelabz.response.Response;
import com.bridgelabz.utility.Tokenutility;
import com.bridgelabz.utility.Utility;

@Service
public class ServiceImp implements com.bridgelabz.services.Service {

	@Autowired
	Userrepo repo;    //create object user repo

	@Autowired
	private JavaMailSender javaMailSender;  //  use JavaMailSender class

	@Autowired
	private PasswordEncoder passwordconfig;   // create object  passwordencoder class

	@Autowired
	Tokenutility tokenutility;      // create object for tokenutility

	@Autowired
	Passwordconfig confing;      // create  object for  confing

	@Autowired
	ModelMapper mapper;         // user modelmapper for store data 

	
	
	/**
	 *    purpose:  add new user detail in database if user add already recored then show user email already existing
	 *              & password store  encrypt format 
	 */
	@Override
	public String addNewUser(Registerdto regdto) {

		User user = mapper.map(regdto, User.class);  //store  new user data in mapper

		if (repo.findAll().stream().anyMatch(i -> i.getEmail().equals(regdto.getEmail())))  //check user already existing or not
		{
			throw new Registrationexcepton(MessageReference.EMAIL_ALREADY_REGISTERED);

		}

		user.setName(regdto.getName());
		user.setEmail(regdto.getEmail());
		user.setAddress(regdto.getAddress());
		user.setPassword(passwordconfig.encode(regdto.getPassword()));
		user = repo.save(user);   //store  user all detail in db

		if (user == null) 
		{
			return "new user not Registered Succssfully";
		}
		String token = tokenutility.createToken(user.getId());
		javaMailSender.send(Utility.verifyUserMail(regdto.getEmail(), token, MessageReference.REGISTRATION_MAIL_TEXT));   //send  verify link message in user email id

		return " new User Registered Successfully...";
	}

	
	/**
	 * @param token user send verify token for checking for token is match or not
	 * @return   give the response for user  email verify or not
	 */
	public Response valivateUser(String token)
	{
		String userid = tokenutility.getUserToken(token);    //get user id from user token
		
		User user = repo.findById(userid).get();  //check userid present or not
		if (user != null) {   //if userid is found  validate should be true
			user.setValidate(true);
			repo.save(user);
			return new com.bridgelabz.response.Response(200, "email  ", "Email Verfiy");
		} else {
			return new com.bridgelabz.response.Response(200, "email not verfiy", "email not verfiy");

		}

	}

	/**
	 *     Purpose : login user though email id or password
	 */
	@Override
	public String loginUser(Logindto loginDTO) {

		User user = repo.findByEmail(loginDTO.getEmail());  //find email present or not
		if (user == null) {
			return "Invalid username and password";

		}

		if (user.getEmail().equals(loginDTO.getEmail())
				&& confing.encoder().matches(loginDTO.getPassword(), user.getPassword())) {   //encode the user password
			return "Login successfully..";
		}
		return "user login not successfully.";
	}

	
	
	
	/**
	 *   find user  present or not  if user found  show user details
	 */
	@Override
	public Optional<User> findByUser(String id) {

		return repo.findById(id);   // find by  user id in mongodb
	}

	/**   show all user details 
	 *
	 */
	@Override
	public List<User> Show() {

		return repo.findAll(); //show  all user details in mongodb
	}

	/**
	 *  purpose : delete  particular user in database though  user id
	 */
	@Override
	public String deleteUser(String id) {
		repo.deleteById(id);    //delete user in db

		return "User delete Succssfully..";
	}

	@Override
	public String updateuser(User user, String id) {

		User userupdate = repo.findById(id).get();
		userupdate = user;
		repo.save(userupdate);
		return "User Update Successfully...";

	}

	public String updateuserByEmail(User user, String email) {

		User userupdate = repo.findByEmail(email);
		userupdate = user;
		repo.save(userupdate);
		return "User Update Successfully...";

	}
	
	
	

	/**
	 *    find user email present or not  and validate for checking send email for verify user  email id
	 */
	@Override
	public Response findEmail(String email) {

		
		User user = repo.findByEmail(email);   // find by  user email id
		System.out.println(user);
		if (user == null) {   //if user email id it null  response to user  not register it

			return new Response(400, "user", "user not existing");

		} else {
			String token = tokenutility.createToken(user.getId());    //user id of user
			javaMailSender.send(Utility.verifyUserMail(email, token, MessageReference.Verfiy_MAIL_TEXT));  //send email from user email id

		}
		return null;
	}

	/**
	 *  Purpose  user  send  new password for changing .password  should  change it
	 */
	@Override
	public Response setPassword(Setpassworddto setpassworddto, String token) {

		String userid = tokenutility.getUserToken(token);
		String email = repo.findById(userid).get().getEmail();   //find user email present or not
		User updateuser = repo.findByEmail(email);
		if (setpassworddto.getPassword().equals(setpassworddto.getCfmpassword())) {  //check password or cfmpassword
			updateuser.setPassword(passwordconfig.encode(setpassworddto.getPassword())); //new password encode it

			updateuserByEmail(updateuser, email);

		}

		return new Response(200, "change password", "Change password successfully..");
	}

}
