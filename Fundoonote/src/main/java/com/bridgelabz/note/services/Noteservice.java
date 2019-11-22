package com.bridgelabz.note.services;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.note.dto.Notedto;
import com.bridgelabz.note.model.Notemodel;

public interface Noteservice {
	
	
	
	public void createNote(Notedto notedto,String token);
	public String deleteNote(String id);
	public void UpdateNote(Notedto notedto,String id);
	public Optional<Notemodel> searchNote(String id);
    public List<Notemodel> showAllNote();
//	

}
