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

import java.util.List;
import java.util.Optional;

import com.bridgelabz.note.dto.Collabratordto;
import com.bridgelabz.note.dto.Notedto;
import com.bridgelabz.note.model.Notemodel;

public interface Noteservice {
	
	
	
	public void createNote(Notedto notedto,String token); //create createNote() method for add new note
	public String deleteNote(String id);                  //create deleteNote() method for delete note
	public void UpdateNote(Notedto notedto,String id);    //create UpdateNote() method for update note 
	public Optional<Notemodel> searchNote(String id);     //create UpdateNote() method for search perticular note
    public List<Notemodel> showAllNote();                 //create showAllNote() method for show all note
    public List<Notemodel> sortNoteByName();              //create sortNoteByName() method for sort note by name
	public  List<Notemodel> sortNoteByDate();             //create sortNoteByDate() method for sort note by date
	public String addCollabrator(Collabratordto collabratorDto);   //create addCollabrator() method for collabrator other user
	
	public boolean archive(String token);    //create archive() method for user archive note
	public boolean pin(String token);        //create UpdateNote() method for user archive pin 
	public boolean trash(String token);       //create UpdateNote() method for  user archive trash
//	 

}
