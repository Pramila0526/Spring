/******************************************************************************
 *  Compilation:  javac -d bin Tokenutility.java
 *  Execution:    
 *               
 *  
 *  Purpose:       create interface for  user label
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since  25-11-2019
 *
 ******************************************************************************/
package com.bridgelabz.note.services;

import java.util.ArrayList;

import com.bridgelabz.note.dto.Labeldto;
import com.bridgelabz.note.model.Labelmodel;
import com.bridgelabz.note.response.Response;

public interface Labelservice {
	
	
	public Response labelAdd(Labeldto labeldto,String token);     //create labelAdd() method for add new label by user
	public Response labelDelete(String id ,String token);                       //create labelDelete() method for delete  label by user
	public Response labelUpdate(Labeldto labeldto, String id ,String token);    //create labelUpdate() method for update  label by user
	public ArrayList<Labelmodel> labelShowAll(String token);              //create labelShowAll() method for show all label by user
	public Response labelSearch(String id ,String token);                   //create labelSearch() method for search label by user
	
	public Response findLabelByUser_id(String user_id ,String token); //create findLabelByUser_id() method for  find  user id present or not
	public Response manyToMany(String noteid,String labelid ,String token);          //create assignNote() method for relationship between two class
	
	 

}
