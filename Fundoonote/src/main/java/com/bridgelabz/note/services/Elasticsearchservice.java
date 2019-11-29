package com.bridgelabz.note.services;

import java.io.IOException;
import java.util.List;

import com.bridgelabz.note.model.Notemodel;
import com.bridgelabz.note.response.Response;

public interface Elasticsearchservice {
	
	
	
	public Response  createDocuemnt(Notemodel note) throws IOException;
	public Response  readDocuement(String id)throws IOException;
	public String    search(String searchstring);
	public Response  deleteDocuemnt(String id)throws IOException;
	public Response  updateDocuemnt(Notemodel note ,String id)throws IOException;
	public List<Notemodel> findAll()throws IOException;
	

}
