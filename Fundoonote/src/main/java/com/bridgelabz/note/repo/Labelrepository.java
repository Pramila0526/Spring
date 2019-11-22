package com.bridgelabz.note.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bridgelabz.note.model.Labelmodel;


public interface Labelrepository extends MongoRepository<Labelmodel, Object> {
	
	public List<Labelmodel> findByUserid(String user_id);
	
	

}
