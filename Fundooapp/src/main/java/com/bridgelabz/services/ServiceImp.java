package com.bridgelabz.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.config.Passwordconfig;
import com.bridgelabz.dto.Forgotdto;
import com.bridgelabz.dto.Logindto;
import com.bridgelabz.dto.Registerdto;
import com.bridgelabz.dto.Setpassworddto;
import com.bridgelabz.exception.Custom.Registrationexcepton;
import com.bridgelabz.model.User;
import com.bridgelabz.repo.Userrepo;
import com.bridgelabz.utility.Tokenutility;
import com.bridgelabz.utility.Utility;
import com.sun.mail.iap.Response;
@Service
public class ServiceImp implements com.bridgelabz.services.Service {

	@Autowired
	Userrepo repo;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private PasswordEncoder passwordconfig;
	
	@Autowired
	Tokenutility tokenutility;
	
	@Autowired
	Passwordconfig  confing;
	

	@Autowired
	ModelMapper mapper;
	

	@Override
	public String  addNewUser(Registerdto regdto)
	{
		boolean validate;
		User user = mapper.map(regdto, User.class);

		if (repo.findAll().stream().anyMatch(i -> i.getEmail().equals(regdto.getEmail())))
		{
          
			throw new Registrationexcepton(MessageReference.EMAIL_ALREADY_REGISTERED);
			
			
		}

		user.setName(regdto.getName());
	
		user.setEmail(regdto.getEmail());        
		user.setAddress(regdto.getAddress());
		user.setPassword(passwordconfig.encode(regdto.getPassword()));
		
	
	 	
	 	
		


		
		
		
		
		
		user = repo.save(user);

		String token=tokenutility.createToken(user.getId());
	 	javaMailSender.send(Utility.verifyUserMail(regdto.getEmail(),token));    
	 	
	   //  valivateUser(token);
		System.out.println("name"+user.getName());
		System.out.println("id"+user.getId());
		if (user == null)
		{
			return "new user not Registered Succssfully";
		}
		return " new User Registered Successfully...";
	}

	public com.bridgelabz.response.Response valivateUser(String token) 
	{
		
		String userid=tokenutility.getUserToken(token);
         
		
		System.out.println("id :"+ userid);
		System.out.println("token"+token);
		
		 if(userid.equals(token)) {
			 return new com.bridgelabz.response.Response(200, "email not verfiy", true);
			 
		 }
		 else {
			 return new com.bridgelabz.response.Response(400, "email  verfiy", true);
		 }
		
		
	
		
		
	}

	@Override
	public String loginUser(Logindto loginDTO) {
		
		User user=repo.findByEmail(loginDTO.getEmail());
		 if(user==null) {
			
			
		 }
		
		if(user.getEmail().equals(loginDTO.getEmail()) && confing.encoder().matches(loginDTO.getPassword(), user.getPassword()))
		{
			return "Login successfully..";
		}
		return "user login not successfully.";
	}

	@Override
	public Optional<User> findByUser(int id) {
		
		return repo.findById(id);
	}

	@Override
	public List<User> Show() {
	
		return repo.findAll();
	}

	@Override
	public String deleteUser(int id) {
		repo.deleteById(id);
		
		return "User delete Succssfully..";
	}

	@Override
	public String updateuser(User user, int id) {
		
		
		User userupdate = repo.findById(id).get();
		userupdate = user;
		repo.save(userupdate);
		return "User Update Successfully...";

	}

	@Override
	public Response forgetPassword(Forgotdto forgetDto) {
		
		return null;
	}

	@Override
	public Response setPassword(Setpassworddto setPasswordDto, String token) {
	
		return null;
	}
	public void validateEmail() 
	{
		
	}

	

}
