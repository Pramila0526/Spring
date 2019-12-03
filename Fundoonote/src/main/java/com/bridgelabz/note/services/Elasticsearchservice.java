/******************************************************************************
 *  Compilation:    javac -d bin Elasticsearchservice.java
 *  Execution:     
 *  
 *  Purpose:       create  service  interface for  elastic search
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since  3-12-2019
 *
 ******************************************************************************/
package com.bridgelabz.note.services;

import java.io.IOException;
import java.util.List;

import com.bridgelabz.note.model.Notemodel;
import com.bridgelabz.note.response.Response;

public interface Elasticsearchservice { // create interface for elastic search

	public Response createDocuemnt(Notemodel note) throws IOException; // create method for storing information

	public Response readDocuement(String id) throws IOException; // create method for read perticular info

	public String search(String searchstring); // create method for search perticualr info

	public Response deleteDocuemnt(String id) throws IOException; // create method for delete perticualr info of user

	public Response updateDocuemnt(Notemodel note, String id) throws IOException; // create method for update info of
																					// perticular user detail

	public List<Notemodel> findAll() throws IOException; // create method for show all information

	public Response searchByTitle(String title) throws IOException; // create method for search by user note title

	public Response searchByDescription(String description) throws IOException; // create method for search by user note
																				// description

}
