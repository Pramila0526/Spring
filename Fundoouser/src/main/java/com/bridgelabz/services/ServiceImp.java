/******************************************************************************
 1 *  Compilation:  javac -d bin Response.java
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties.Storage;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.config.Passwordconfig;
import com.bridgelabz.dto.Logindto;
import com.bridgelabz.dto.Registerdto;
import com.bridgelabz.dto.Setpassworddto;
import com.bridgelabz.exception.Custom.Forgotpasswordexception;
import com.bridgelabz.exception.Custom.Registrationexcepton;
import com.bridgelabz.exception.Custom.Validateuserexception;
import com.bridgelabz.exception.Custom.Deleteexception;
import com.bridgelabz.model.Rabbitmqmodel;
import com.bridgelabz.model.User;
import com.bridgelabz.repo.Userrepo;
import com.bridgelabz.response.Response;
import com.bridgelabz.utility.Tokenutility;
import com.bridgelabz.utility.Utility;

@Service
public class ServiceImp implements com.bridgelabz.services.Service {

	static Logger logger = LoggerFactory.getLogger(ServiceImp.class);
	@Autowired
	private Userrepo repo; // create object user repo

	@Autowired
	private JavaMailSender javaMailSender; // use JavaMailSender class

	@Autowired
	private PasswordEncoder passwordConfig; // create object passwordencoder class

	@Autowired
	private Tokenutility tokenutility; // create object for tokenutility

	@Autowired
	private Passwordconfig confing; // create object for confing

	@Autowired
	ModelMapper mapper; // user modelmapper for store data

	@Autowired
	RabbitTemplate template;
	//private final String path = "/home/user/Documents/Springboot/Fundoouser/Profile/";
	

	/**
	 * purpose: add new user detail in database if user add already recored then
	 * show user email already existing & password store encrypt format
	 */

//	@Value("${key}")
//	private String key;
	@Override
	public Response addNewUser(@Valid Registerdto regdto) {

		User user = mapper.map(regdto, User.class); // store new user data in mapper

		if (repo.findAll().stream().anyMatch(i -> i.getEmail().equals(regdto.getEmail()))) // check user already
																							// existing or not
		{
			throw new Registrationexcepton(MessageReference.EMAIL_ALREADY_REGISTERED);

		}
		user.setFirstname(regdto.getFirstname());
		user.setLastname(regdto.getLastname());
		user.setEmail(regdto.getEmail());
		user.setPhonenumber(regdto.getPhonenumber());
		user.setPassword(passwordConfig.encode(regdto.getPassword()));
		user = repo.save(user); // store user all detail in db
		if (user == null) {
			throw new Registrationexcepton(MessageReference.EMAIL_FAIL);
		}

		String token = tokenutility.createToken(user.getId());
		// Rabbitmqmodel body = Utility.getRabbitMq(regdto.getEmail(), token);
		// template.convertAndSend("userMessageQueue", body);
		// javaMailSender.send(Utility.getRabbitMq(regdto.getEmail(), token));
		// javaMailSender.send(Utility.verifyUserMail(regdto.getEmail(), token,
		// MessageReference.REGISTRATION_MAIL_TEXT)); // send
		logger.isWarnEnabled();

		String gettoken = tokenutility.getUserToken(token);
		// redisTemp.opsForValue().set(key, gettoken);

		return new Response(200, "User Registrtion ", MessageReference.USER_ADD_SUCCESSFULLY);
	}

	/**
	 * @param token user send verify token for checking for token is match or not
	 * @return give the response for user email verify or not
	 */

	public Response valivateUser(String token) {

		String userid = tokenutility.getUserToken(token); // get user id from user token

		User user = repo.findById(userid).get(); // check userid present or not
		if (user != null) { // if userid is found validate should be true
			user.setValidate(true);
			repo.save(user);
			return new Response(200, "email  ", MessageReference.EMAIL_VERFIY);
		} else {
			return new Response(200, "email not verfiy", MessageReference.NOT_VERFIY_EMAIL);

		}

	}

	/**
	 * Purpose : login user though email id or password
	 */

	@Cacheable(value = "user", key = "#token")
	@Override
	public Response loginUser(Logindto loginDTO, String token) {
		System.out.println("server");
		System.out.println(loginDTO.getEmail());
		User user = repo.findByEmail(loginDTO.getEmail()); // find email present or not
		System.out.println(user);
		if (user == null) {
			return new Response(200, "User Registrtion ", MessageReference.EMAIL_FAIL);

		}
		if (!user.isValidate()) {

			new Validateuserexception(MessageReference.NOT_ACTIVE);
		} else {
			if (user.getEmail().equals(loginDTO.getEmail())
					&& confing.encoder().matches(loginDTO.getPassword(), user.getPassword())) { // encode the user
				return new Response(200, "User Registrtion ", MessageReference.LOGIN_SUCCESSFULLY); // password

			}
		}

		return new Response(200, "User Registrtion ", MessageReference.LOGIN_NOT_SUCCESSFULLY);

	}

	/**
	 * find user present or not if user found show user details
	 */
	@Override
	public Response findByUser(String id) {

		return new Response(200, "User Registrtion ", repo.findById(id));

		// find by user id in mongodb
	}

	/**
	 * show all user details
	 *
	 */
	@Override
	public List<User> Show() {

		return repo.findAll(); // show all user details in mongodb
	}

	/**
	 * purpose : delete particular user in database though user id
	 */
	@Override
	public Response deleteUser(String id) {
		User user_id = repo.findById(id).get();
		if (user_id == null) {
			throw new Deleteexception(MessageReference.USER_ID_NOT_FOUND);
		}
		repo.deleteById(id); // delete user in db

		return new Response(200, "User Registrtion ", MessageReference.USER_DELETE_SUCCESSFULLY);

	}

	@Override
	public Response updateuser(User user, String id) {

		User userupdate = repo.findById(id).get();

		userupdate = user;
		repo.save(userupdate);
		return new Response(200, "User Registrtion ", MessageReference.USER_UPDATE_SUCCESSFULLY);

	}

	public String updateuserByEmail(User user, String email) {

		User userupdate = repo.findByEmail(email);
		userupdate = user;
		repo.save(userupdate);
		return MessageReference.USER_UPDATE_SUCCESSFULLY;
	}

	/**
	 * find user email present or not and validate for checking send email for
	 * verify user email id
	 */
	@Override
	public Response findEmail(String email) {

		User user = repo.findByEmail(email); // find by user email id

		System.out.println(user);
		if (user == null) { // if user email id it null response to user not register it

			throw new Forgotpasswordexception(MessageReference.USER_NOT_EXISTING);

		} else {

			String token = tokenutility.createToken(user.getId()); // user id of user
			Rabbitmqmodel body = Utility.getRabbitMq(email, token);
			template.convertAndSend("userMessageQueue", body);
			javaMailSender.send(Utility.verifyUserMail(email, token, MessageReference.Verfiy_MAIL_TEXT)); // send email
																											// from user
																											// email id

		}
		return new Response(400, "user", "user  email found");

	}

	/**
	 * Purpose user send new password for changing password should change it
	 */
	@Override
	public Response setPassword(Setpassworddto setPasswordDto, String token) {

		String userid = tokenutility.getUserToken(token);
		String email = repo.findById(userid).get().getEmail(); // find user email present or not
		System.out.println(email);
		User updateuser = repo.findByEmail(email);
		if (setPasswordDto.getPassword().equals(setPasswordDto.getCfmpassword())) { // check password or cfmpassword

			updateuser.setPassword(passwordConfig.encode(setPasswordDto.getPassword())); // new password encode it

			updateuserByEmail(updateuser, email);
			return new Response(200, "change password", MessageReference.PASSWORD_CHANGE_SUCCESSFULLY);

		} else {
			return new Response(200, "change password", MessageReference.PASSWORD_IS_NOT_MATCHING);

		}

	}

	/**
	 *   purpose 
	 */
		
	@Override
	public Response addProfile(MultipartFile file, String userid) throws IOException { 
		System.out.println(file);
		Optional<User> getUser = repo.findById(userid);
		if(getUser.isEmpty())
		{
			  throw new   Registrationexcepton(MessageReference.USER_ID_NOT_FOUND);
		}
			User user = getUser.get();
		if (user != null && userid != null) {
			if (file.getOriginalFilename().contains(".jpg") || file.getOriginalFilename().contains(".png")
					|| file.getOriginalFilename().contains(".jpeg")) {
				if (!file.isEmpty()) {
					
					
					   File filepath=new File("/home/user/Documents/Springboot/Fundoouser/Profile/"+file.getOriginalFilename());
					   filepath.createNewFile();
					   
					
					FileOutputStream fo = new FileOutputStream(file.getOriginalFilename());
				    String pic =  file.getOriginalFilename();
				    
					user.setProfile(pic);
					System.out.println(file.getBytes());
					fo.write(file.getBytes());
					repo.save(user);

				}
			}
		}

		return new Response(200 ,"profile", MessageReference.USER_PROFILE_ADD);
	}

	@Override
	public Response deleteProfile(String  profileName, String userid) {
		
		Optional<User> getUser=repo.findById(userid);
		if(getUser.isEmpty()) {
			throw new  Registrationexcepton(MessageReference.USER_ID_NOT_FOUND);
		}
		User user=getUser.get();
		if(userid!=null && user!=null) {	
			
			
			File filepath=new File("/home/user/Documents/Springboot/Fundoouser/Profile/"+profileName);
			System.out.println(filepath);
			   filepath.delete();
			   
			
			user.setProfile(null);
			repo.save(user);
		}
		

		return new Response(200, MessageReference.USER_PROFILE_REMOVE, true);
	}

	@Override
	public ResponseEntity<Resource> getProfile( String userid,HttpServletRequest request ) throws IOException {
		     Optional<User>id=repo.findById(userid);
		     if(id.isEmpty()) {
		    	 throw new Registrationexcepton(MessageReference.USER_ID_NOT_FOUND);
		     }
		     User user=id.get();
		     
		     String  filepath=user.getProfile();
			String path="/homeuser/Documents/Springboot/Fundoouser/Profile/"+filepath;
			System.out.println(path);
		   Path filePath = Paths.get(path);
		   Resource resource=new UrlResource(filePath.toUri());
		   String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		   if(contentType==null)
			   contentType="application/octate-stream";
		   return ResponseEntity.ok()
				   .contentType(MediaType.parseMediaType(contentType))
				   .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+resource.getFilename()+"\"")
				   .body(resource);
		   
	}

}
