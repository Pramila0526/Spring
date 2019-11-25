/******************************************************************************
 *  Compilation:  javac -d bin Noterepository.java
 *  Execution:    
 *               
 *  
 *  Purpose:create  interface for  communicate mongo database
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since  25-11-2019
 *
 ******************************************************************************/
package com.bridgelabz.note.repo;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.bridgelabz.note.model.Notemodel;

public interface Noterepository extends MongoRepository<Notemodel, Object> {  // create interface  and extends mongo  Repository
	
	
	public List<Notemodel> findByUserid(String userid);
	

}
