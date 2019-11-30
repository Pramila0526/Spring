/******************************************************************************
 *  Compilation:  javac -d bin Tokenutility.java
 *  Execution:    
 *               
 *  
 *  Purpose:       create  serviceimp class for note user
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since  25-11-2019
 *
 ******************************************************************************/
package com.bridgelabz.note.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.bridgelabz.note.dto.Collabratordto;
import com.bridgelabz.note.dto.Notedto;

import com.bridgelabz.note.exception.custom.Notenotfoundexception;
import com.bridgelabz.note.exception.custom.Usernotfoundexception;
import com.bridgelabz.note.model.Notemodel;
import com.bridgelabz.note.repo.Noterepository;
import com.bridgelabz.note.response.Response;
import com.bridgelabz.note.utility.Tokenutility;

@Service
public class NoteserviceImp implements Noteservice {
	static  Logger logger=LoggerFactory.getLogger(LabelserviceImp.class);
	@Autowired
	Noterepository repo; // create Noterepository object

	@Autowired
	ModelMapper mapper; // create ModelMapper object

	@Autowired
	Tokenutility tokenutility; // create Tokenutility object
	
	
	@Autowired
	ElasticsearchserviceImp elasticsearchserviceImp;

	/**
	 * purpose add new user note
	 */
	/**
	 * @throws IOException 
	 *
	 */
	@Override
	public Response createNote(Notedto notedto, String token) throws IOException {

		Notemodel notemodel = mapper.map(notedto, Notemodel.class);

		notemodel.setTitle(notedto.getTitle());
		notemodel.setDescription(notedto.getDescription());
		notemodel.setColor(notedto.getColor());
		LocalDateTime datetime = LocalDateTime.now();
		notemodel.setDate(datetime);
		
		String user_id = tokenutility.getUserToken(token);
		
		notemodel.setUserid(user_id);
         
		repo.save(notemodel);
	
		elasticsearchserviceImp.createDocuemnt(notemodel);
		return new Response(200, "note add", MessageReference.NOTE_ADD_SUCCESSFULLY);
	}

	/**
	 * purpose delete perticular note
	 */
	@Override
	public Response deleteNote(String id) {

		Notemodel note_id = repo.findById(id).get();
		if (note_id == null) {
			throw new Notenotfoundexception(MessageReference.NOTE_ID_NOT_FOUND);
		}
		System.out.println(note_id);
		repo.delete(note_id);

		return new Response(200, "note add", MessageReference.NOTE_DELETE_SUCCESSFULLY);

	}

	/**
	 * purpose Search a perticular user note
	 */
	@Override
	public Response searchNote(String id) {

		Notemodel note_id = repo.findById(id).get();
		if (note_id == null) {
			throw new Notenotfoundexception(MessageReference.NOTE_ID_NOT_FOUND);
		}

		return new Response(200, "Note search", repo.findById(id));

	}

	/**
	 * purpose show all user note
	 */
	@Override
	public List<Notemodel> showAllNote() {

		return repo.findAll();
	}

	/**
	 * purpose update perticular Note
	 */
	@Override
	public Response UpdateNote(Notedto notedto, String id) {

		Notemodel updateNote = repo.findById(id).get();

		if (updateNote == null) {
			throw new Notenotfoundexception(MessageReference.NOTE_ID_NOT_FOUND);
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

		return new Response(200, "Note update", MessageReference.NOTE_UPDATE_SUCCESSFULLY);

	}

	/**
	 * sort note by name
	 */
	@Override
	public Response sortNoteByName() {

		List<Notemodel> note = showAllNote();
		if (note.isEmpty()) {
			return new Response(200, "sort by name", MessageReference.NOTE_IS_EMPTY);
		}

		note.stream().sorted((note1, note2) -> note1.getTitle().compareTo(note2.getTitle()))
				.collect(Collectors.toList());

		return new Response(200, "Sort note by name", note);
	}

	/**
	 * sort note by date
	 */
	@Override
//	public List<Notemodel> sortNoteByDate() {
	public Response sortNoteByDate() {

		List<Notemodel> note = showAllNote();
		if (note.isEmpty()) {
			return new Response(200, "sort by date", MessageReference.NOTE_IS_EMPTY);
		}
		note.stream().sorted((note1, note2) -> note1.getDate().compareTo(note2.getDate())).collect(Collectors.toList());
		return new Response(200, "sort by date", note);
	}

	/**
	 * collabrator other user
	 * 
	 */
	@Override
	public Response addCollabrator(Collabratordto collabratorDto) {

		Notemodel note = repo.findById(collabratorDto.getNoteId()).get();

		if (note == null) {
			throw new Usernotfoundexception(MessageReference.NOTE_ID_NOT_FOUND);
		}

		List<String> list = new ArrayList<String>();
		list = note.getCollabrators();
		list.add(collabratorDto.getColaboratorId());
		note.setCollabrators(list);
		repo.save(note);

		return new Response(200, "add collbrator", "collabrator add successfully");

	}

	@Override
	public boolean archive(String token) {

//		String userid = tokenutility.getUserToken(token);
//		if (userid != null) {
//			Notemodel note = (Notemodel) repo.findByUserid(userid);
//			if (note != null) {
//				note.setArchive(!(note.isArchive()));
//				repo.save(note);
//				return true;
//
//			}
//		}
		return false;
	}

	@Override
	public boolean pin(String token) {
//
//		String userid = tokenutility.getUserToken(token);
//		if (userid != null) {
//			Notemodel note = (Notemodel) repo.findByUserid(userid);
//			if (note != null) {
//				note.setPin(!(note.isPin()));
//				repo.save(note);
//				return true;
//
//			}
//		}
		return false;
	}

	@Override
	public boolean trash(String token) {
//		String userid = tokenutility.getUserToken(token);
//		if (userid != null) {
//			Notemodel note = (Notemodel) repo.findByUserid(userid);
//			if (note != null) {
//				note.setTrash(!(note.isTrash()));
//				repo.save(note);
//				return true;
//
//			}
//
//		}
		return false;
	}

	
	
	@Override
	public Response searchByTitle(String title) throws Exception {	
		
		
		 return new Response(200,  "user title", elasticsearchserviceImp.searchByTitle(title));
	}
}
