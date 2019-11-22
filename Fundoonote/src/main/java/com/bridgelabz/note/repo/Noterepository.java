package com.bridgelabz.note.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bridgelabz.note.model.Notemodel;

public interface Noterepository extends MongoRepository<Notemodel, Object> {
	
	
	
	

}
