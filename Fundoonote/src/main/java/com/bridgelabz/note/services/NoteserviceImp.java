package com.bridgelabz.note.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.note.dto.Notedto;
import com.bridgelabz.note.exception.Deleteexception;
import com.bridgelabz.note.model.Notemodel;
import com.bridgelabz.note.repo.Noterepository;
import com.bridgelabz.note.utility.Tokenutility;

@Service
public class NoteserviceImp implements Noteservice {

	
	@Autowired Noterepository repo;
	
	@Autowired
	ModelMapper mapper;
	
   @Autowired
   Tokenutility tokenutility;
	
	@Override
	public void createNote(Notedto notedto,String token) {
		 
		Notemodel notemodel= mapper.map(notedto, Notemodel.class);
	
		notemodel.setTitle(notedto.getTitle());		
		notemodel.setDescription(notedto.getDescription());
		notemodel.setColor(notedto.getColor());
		LocalDateTime datetime =LocalDateTime.now();
		notemodel.setCreateDate(datetime);		
		String user_id=tokenutility.getUserToken(token);
		notemodel.setUser_id(user_id);
		 	
		repo.save(notemodel);
				
	}




	@Override
	public String deleteNote(String  id) {
		
		Notemodel note_id=repo.findById(id).get();
		if(note_id==null) {
			throw new Deleteexception(MessageReference.NOTE_ID_NOT_FOUND);
		}
		System.out.println(note_id);
		repo.delete(note_id);
		
		return MessageReference.NOTE_DELETE_SUCCESSFULLY;
	
	}


	@Override
	public Optional<Notemodel> searchNote(String id) {	
		
		return  repo.findById(id);
		
	}




	@Override
	public List<Notemodel> showAllNote() {
		
		return repo.findAll();
	}




	@Override
	public void UpdateNote(Notedto notedto ,String id) {
		
		Notemodel updateNote=repo.findById(id).get();

	if(updateNote==null) {
			throw new Deleteexception(MessageReference.NOTE_ID_NOT_FOUND);
		}
		System.out.println(notedto.getColor());
		System.out.println(notedto.getDescription());
		System.out.println(notedto.getTitle());
		updateNote.setColor(notedto.getColor());
		updateNote.setDescription(notedto.getDescription());
		updateNote.setTitle(notedto.getTitle());
		LocalDateTime datetime =LocalDateTime.now();
		updateNote.setUpdateDate(datetime);
		
		
		
	    repo.save(updateNote);
		
		
	}

}
