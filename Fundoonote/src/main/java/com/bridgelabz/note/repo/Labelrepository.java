/******************************************************************************
 *  Compilation:  javac -d bin Labelrepository.java
 *  Execution:    
 *               
 *  
 *  Purpose:       create  interface for  communicate mongo database
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since  19-11-2019
 *
 ******************************************************************************/
package com.bridgelabz.note.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bridgelabz.note.model.Labelmodel;

public interface Labelrepository extends MongoRepository<Labelmodel, Object> { // create interface & extends
																				// MongoRepository for user label

	public List<Labelmodel> findByUserid(String user_id); // create method for fetch user userid

	public Optional<Labelmodel> findByIdAndUserid(String labelid, String userid);

}
