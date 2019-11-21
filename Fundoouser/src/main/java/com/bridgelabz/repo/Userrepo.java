/******************************************************************************
 *  Compilation:  javac -d bin Userrepo.java
 *  Execution:    
 *               
 *  
 *  Purpose:       create repository class for db connection with mongo database
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since  19-11-2019
 *
 ******************************************************************************/

package com.bridgelabz.repo;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.model.User;

@Repository
public interface Userrepo extends MongoRepository<User, Object> {  //create interface for mongodb repository use it
	
	
	public User findByEmail(String email);   // find  user email
	
	


}
