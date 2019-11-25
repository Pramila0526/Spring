/******************************************************************************
 *  Compilation:  javac -d bin Tokenutility.java
 *  Execution:    
 *               
 *  
 *  Purpose:       create utility for jwt  response in  token
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since  19-11-2019
 *
 ******************************************************************************/
package com.bridgelabz.note.services;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.note.dto.Collabratordto;
import com.bridgelabz.note.dto.Notedto;
import com.bridgelabz.note.model.Notemodel;

public interface Noteservice {
	
	
	
	public void createNote(Notedto notedto,String token);
	public String deleteNote(String id);
	public void UpdateNote(Notedto notedto,String id);
	public Optional<Notemodel> searchNote(String id);
    public List<Notemodel> showAllNote();
    public List<Notemodel> sortNoteByName();

	public  List<Notemodel> sortNoteByDate();
	public String addCollabrator(Collabratordto collabratorDto);
	
	public boolean archive(String token);
	public boolean pin(String token);
	public boolean trash(String token);
//	

}
