package com.bridgelabz.note.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.note.dto.Notedto;
import com.bridgelabz.note.exception.Deleteexception;
import com.bridgelabz.note.model.Notemodel;
import com.bridgelabz.note.repo.Repo;

@Service
public class NoteserviceImp implements Noteservice {

	
	@Autowired Repo repo;
	
	@Autowired
	ModelMapper mapper;
	


	
	@Override
	public void createNote(Notedto notedto) {
		 
		Notemodel notemodel= mapper.map(notedto, Notemodel.class);
	
		notemodel.setTitle(notedto.getTitle());		
		notemodel.setDescription(notedto.getDescription());
		notemodel.setColor(notedto.getColor());
		 	
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
		System.out.println(updateNote.toString());
//		if(updateNote==null) {
//			throw new Deleteexception(MessageReference.NOTE_ID_NOT_FOUND);
//		}
		System.out.println(notedto.getColor());
		System.out.println(notedto.getDescription());
		System.out.println(notedto.getTitle());
		updateNote.setColor(notedto.getColor());
		updateNote.setDescription(notedto.getDescription());
		updateNote.setTitle(notedto.getTitle());
		
		
	    repo.save(updateNote);
		
		
	}

}
