package com.bridgelabz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bridgelabz.dto.Registerdto;
import com.bridgelabz.model.User;
import com.bridgelabz.repo.Userrepo;
import com.bridgelabz.response.Response;
import com.bridgelabz.services.ServiceImp;
import com.bridgelabz.utility.Tokenutility;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Usertest {
	
	@Mock
	Userrepo repo;
	
	@InjectMocks
	 ServiceImp serviceImp; 
	@Mock
	ModelMapper mapper;
	@Mock
	Tokenutility tokenutility;
	String token="gghffggfjdfjg";
	String id="1";
	String emailid="panditwalde64@gmail.com";
	User u=new User();
	
	@Test
	public void showAlluser() {
		User user=new User();
		user.setId(id);
		user.setFirstname("pandit");
		user.setLastname("walde");
		user.setEmail(emailid);
		user.setPassword("abcd");
		user.setProfile("dggdgg");
		user.setValidate(true);
		
		User user1=new User();
		user1.setId(id);
		user1.setFirstname("ajay");
		user1.setLastname("lodale");
		user1.setEmail(emailid);
		user1.setPassword("abdggfdfgcd");
		user1.setProfile("abc.png");
		user1.setValidate(true);
		
		ArrayList<User> list=new ArrayList<User>();
		list.add(user);
		list.add(user1);
		
		when(repo.findAll()).thenReturn(list);
		List<User>listofuser=serviceImp.Show(token);
		assertEquals("pandit", listofuser.get(0).getFirstname());
		
		
	}
	@Test
	public void addUser() {
		PasswordEncoder passwordConfig = null;
		Registerdto userdto=new Registerdto();		
		when(mapper.map(userdto, User.class)).thenReturn(u);
		
		u.setPassword(passwordConfig.encode("dfgg"));		
		when(repo.save(u)).thenReturn(u);		
		when(tokenutility.createToken(id)).thenReturn(token);
		Response res=serviceImp.addNewUser(userdto);
		
		assertEquals(200,res.getStatus());
		
		
	}
	

}
