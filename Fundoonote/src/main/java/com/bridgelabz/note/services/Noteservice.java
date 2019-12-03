/******************************************************************************
 *  Compilation:  javac -d bin Tokenutility.java
 *  Execution:    
 *               
 *  
 *  Purpose:       create interface for   user note
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since  25-11-2019
 *
 ******************************************************************************/
package com.bridgelabz.note.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;


import com.bridgelabz.note.dto.Collabratordto;
import com.bridgelabz.note.dto.Notedto;
import com.bridgelabz.note.model.Notemodel;
import com.bridgelabz.note.response.Response;

public interface Noteservice {
	
	
	
	public Response createNote(Notedto notedto,String token) throws IOException; //create createNote() method for add new note
	public Response deletePermanentNote(String id);                  //create deleteNote() method for delete note
	public Response UpdateNote(Notedto notedto,String id);    //create UpdateNote() method for update note 
	public Response searchNote(String id);     //create UpdateNote() method for search perticular note
    public List<Notemodel>  showAllNote();                 //create showAllNote() method for show all note
    public Response sortNoteByName();              //create sortNoteByName() method for sort note by name
	public Response sortNoteByDate();             //create sortNoteByDate() method for sort note by date
	public Response addCollabrator(Collabratordto collabratorDto);   //create addCollabrator() method for collabrator other user
	public boolean archive(String token);    //create archive() method for user archive note
	public boolean pin(String token);        //create UpdateNote() method for user archive pin 
	public boolean trash(String token);       //create UpdateNote() method for  user archive trash
    public Response addReminder(Date date,String noteid); // create method for  add reminder
    public Response removeReminder(String noteid); // create method for remove reminder
	//	 

}
