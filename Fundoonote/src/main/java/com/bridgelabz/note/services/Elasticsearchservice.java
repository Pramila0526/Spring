package com.bridgelabz.note.services;

import java.io.IOException;

import com.bridgelabz.note.model.Notemodel;
import com.bridgelabz.note.response.Response;

public interface Elasticsearchservice {
	
	
	
	public Response createDocuemnt(Notemodel note) throws IOException;
	public String readDocuement(String id);
	public String search(String searchstring);

}
