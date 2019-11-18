package com.bridgelabz.repo;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.model.User;

@Repository
public interface Userrepo extends MongoRepository<User, Object> {  //create interface for mongodb repository use it
	
	
	public User findByEmail(String email);
	
	


}
