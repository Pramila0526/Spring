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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.note.dto.Collabratordto;
import com.bridgelabz.note.dto.Notedto;
import com.bridgelabz.note.exception.Deleteexception;
import com.bridgelabz.note.model.Notemodel;
import com.bridgelabz.note.repo.Noterepository;
import com.bridgelabz.note.utility.Tokenutility;

@Service
public class NoteserviceImp implements Noteservice {

	@Autowired
	Noterepository repo;

	@Autowired
	ModelMapper mapper;

	@Autowired
	Tokenutility tokenutility;

	@Override
	public void createNote(Notedto notedto, String token) {

		Notemodel notemodel = mapper.map(notedto, Notemodel.class);

		notemodel.setTitle(notedto.getTitle());
		notemodel.setDescription(notedto.getDescription());
		notemodel.setColor(notedto.getColor());
		LocalDateTime datetime = LocalDateTime.now();
		notemodel.setDate(datetime);
		String user_id = tokenutility.getUserToken(token);
		notemodel.setUserid(user_id);

		repo.save(notemodel);

	}

	@Override
	public String deleteNote(String id) {

		Notemodel note_id = repo.findById(id).get();
		if (note_id == null) {
			throw new Deleteexception(MessageReference.NOTE_ID_NOT_FOUND);
		}
		System.out.println(note_id);
		repo.delete(note_id);

		return MessageReference.NOTE_DELETE_SUCCESSFULLY;

	}

	@Override
	public Optional<Notemodel> searchNote(String id) {

		return repo.findById(id);

	}

	@Override
	public List<Notemodel> showAllNote() {

		return repo.findAll();
	}

	@Override
	public void UpdateNote(Notedto notedto, String id) {

		Notemodel updateNote = repo.findById(id).get();

		if (updateNote == null) {
			throw new Deleteexception(MessageReference.NOTE_ID_NOT_FOUND);
		}
		System.out.println(notedto.getColor());
		System.out.println(notedto.getDescription());
		System.out.println(notedto.getTitle());
		updateNote.setColor(notedto.getColor());
		updateNote.setDescription(notedto.getDescription());
		updateNote.setTitle(notedto.getTitle());
		LocalDateTime datetime = LocalDateTime.now();
		updateNote.setDate(datetime);

		repo.save(updateNote);

	}

	@Override
	public List<Notemodel> sortNoteByName() {

		List<Notemodel> note = showAllNote();

		return (List<Notemodel>) note.stream().sorted((note1, note2) -> note1.getTitle().compareTo(note2.getTitle()))
				.collect(Collectors.toList());

	}

	@Override
	public List<Notemodel> sortNoteByDate() {

		List<Notemodel> note = showAllNote();
		return note.stream().sorted((note1, note2) -> note1.getDate().compareTo(note2.getDate()))
				.collect(Collectors.toList());
	}

	@Override
	public String addCollabrator(Collabratordto collabratorDto) {

		Notemodel ownerNote = repo.findById(collabratorDto.getOwnerId()).get();
		List<String> list = new ArrayList<String>();
		list = ownerNote.getCollabrators();
		list.add(collabratorDto.getColaboratorId());
		ownerNote.setCollabrators(list);
		repo.save(ownerNote);

		return "collabrator add successfully";

	}
	

	
	@Override
	public boolean archive(String token) {

		String userid = tokenutility.getUserToken(token);
		if (userid != null) {
			Notemodel note = (Notemodel) repo.findByUserid(userid);
			if (note != null) {
				note.setArchive(!(note.isArchive()));
				repo.save(note);
				return true;

			}
		}
		return false;
	}
	
	

	@Override
	public boolean pin(String token) {

		String userid = tokenutility.getUserToken(token);
		if (userid != null) {
			Notemodel note = (Notemodel) repo.findByUserid(userid);
			if (note != null) {
				note.setPin(!(note.isPin()));
				repo.save(note);
				return true;

			}
		}
		return false;
	}
	

	@Override
	public boolean trash(String token) {
		String userid = tokenutility.getUserToken(token);
		if (userid != null) {
			Notemodel note = (Notemodel) repo.findByUserid(userid);
			if (note != null) {
				note.setTrash(!(note.isTrash()));
				repo.save(note);
				return true;

			}

		}
		return false;
	}
}
