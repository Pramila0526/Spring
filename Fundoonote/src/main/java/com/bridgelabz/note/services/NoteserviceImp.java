/******************************************************************************
 *  Compilation:  javac -d bin LabelserviceImp.java
 *  Execution:    
 *               
 *  
 *  Purpose:       create serviceimp class for write all logic of note
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since  19-11-2019
 *
 ******************************************************************************/
package com.bridgelabz.note.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.bridgelabz.note.dto.Collabratordto;
import com.bridgelabz.note.dto.Notedto;
import com.bridgelabz.note.exception.custom.Notenotfoundexception;
import com.bridgelabz.note.exception.custom.Tokenexception;
import com.bridgelabz.note.exception.custom.Usernotfoundexception;
import com.bridgelabz.note.model.Notemodel;
import com.bridgelabz.note.repo.Noterepository;
import com.bridgelabz.note.response.Response;
import com.bridgelabz.note.utility.Tokenutility;

@Service
@CacheConfig(cacheNames = "note")
public class NoteserviceImp implements Noteservice {
	static Logger logger = LoggerFactory.getLogger(LabelserviceImp.class);
	@Autowired
	private Noterepository repo; // create Noterepository object
	@Autowired
	private ModelMapper mapper; // create ModelMapper object
	@Autowired
	private Tokenutility tokenutility; // create Tokenutility object
	@Autowired
	private ElasticsearchserviceImp elasticsearchserviceImp;

	/**
	 * purpose add new user note
	 */
	/**
	 * @throws IOException
	 *
	 */
	@Override
	@Cacheable(key = "#token")
	public Response createNote(Notedto notedto, String token) throws IOException {
		
		Notemodel notemodel = mapper.map(notedto, Notemodel.class);	    
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
	@CacheEvict(key = "#token")
	public Response deletePermanentNote(String noteid, String token) {
		System.out.println("noteid  "+noteid+"token"+token);
		String userid = tokenutility.getUserToken(token);
		
		if (userid.isEmpty()) {
			throw new Tokenexception(MessageReference.INVALID_TOKEN);
		}
		
		Optional<Notemodel> id = repo.findByIdAndUserid(noteid, userid);
		System.out.println(id);
//		Notemodel note_id = id.get();
		if (id.isEmpty()) {
			throw new Notenotfoundexception(MessageReference.NOTE_ID_NOT_FOUND);
		}
		repo.delete(id.get());
		return new Response(200, "note add", MessageReference.NOTE_DELETE_SUCCESSFULLY);

	}

	/**
	 * purpose Search a perticular user note
	 */
	@Override
	public Response searchNote(String noteid, String token) {

		String userid = tokenutility.getUserToken(token);
		if (userid.isEmpty()) {
			throw new Tokenexception(MessageReference.INVALID_TOKEN);
		}
		return new Response(200, "Note search", repo.findByIdAndUserid(noteid, userid));

	}

	/**
	 * purpose show all user note
	 */
	@Override
	public List<Notemodel> showAllNote(String token) {
		String userid = tokenutility.getUserToken(token);
		if (userid.isEmpty()) {
			throw new Tokenexception(MessageReference.INVALID_TOKEN);
		}
		List<Notemodel> list = repo.findByUserid(userid);
		list.stream().filter(i -> !i.isTrash() && !i.isArchive()).collect(Collectors.toList());
		return list;
	}

	/**
	 * purpose update perticular Note
	 */
	@Override
	public Response UpdateNote(Notedto notedto, String noteid, String token) {

		String userid = tokenutility.getUserToken(token);
		if (userid.isEmpty()) {
			throw new Tokenexception(MessageReference.INVALID_TOKEN);
		}

		Optional<Notemodel> id = repo.findByIdAndUserid(noteid, userid);
		if (id.isEmpty()) {
			throw new Notenotfoundexception(MessageReference.NOTE_ID_NOT_FOUND);
		}
		Notemodel updateNote = id.get();
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
	public Response sortNoteByName(String token) {

		List<Notemodel> note = showAllNote(token);
		if (note.isEmpty()) {
			return new Response(200, "sort by name", MessageReference.NOTE_IS_EMPTY);
		}
        
		note.sort((note1, note2) -> note1.getTitle().compareTo(note2.getTitle()));
	

		return new Response(200, "Sort note by name", note);
	}

	/**
	 * sort note by date
	 */
	@Override
	public Response sortNoteByDate(String token) {

		List<Notemodel> note = showAllNote(token);
		if (note.isEmpty()) {
			return new Response(200, "sort by date", MessageReference.NOTE_IS_EMPTY);
		}
		note.sort((note1, note2) -> note1.getDate().compareTo(note2.getDate()));
		return new Response(200, "sort by date", note);
	}

	/**
	 * collabrator other user
	 * 
	 */

	@Override
	public Response addCollabrator(Collabratordto collabratorDto, String token) {

		String userid = tokenutility.getUserToken(token);
		if (userid.isEmpty()) {
			throw new Tokenexception(MessageReference.INVALID_TOKEN);
		}

		Optional<Notemodel> id = repo.findByIdAndUserid(collabratorDto.getNoteId(), userid);
		if (id.isEmpty()) {
			throw new Usernotfoundexception(MessageReference.NOTE_ID_NOT_FOUND);
		}
		Notemodel note = id.get();

		
		List<String> list = new ArrayList<String>();
		list = note.getCollabrators();
		list.add(collabratorDto.getColaboratorId());
		note.setCollabrators(list);
		repo.save(note);

		return new Response(200, "add collbrator", "collabrator add successfully");

	}

	@Override
	public Response removeCollabrator(String email, String token) {
		
		String userid=tokenutility.getUserToken(token);
		if(userid.isEmpty()) {throw new Tokenexception(MessageReference.INVALID_TOKEN);}
		List<Notemodel> list=repo.findByUserid(userid);
		System.out.println(list);
		
	
		return new Response(200, "collabrator remove succssfully", true);
	}
	/**
	 * purpose create method for archive user note and label
	 */
	@Override
	public Response archive(String token, String noteid) {

		String userid = tokenutility.getUserToken(token); // if token is invalid then throw exception invalid token
		if (userid.isEmpty()) {
			throw new Tokenexception(MessageReference.INVALID_TOKEN);
		}

		Optional<Notemodel> id = repo.findByIdAndUserid(noteid, userid);

		if (id.isEmpty()) {
			throw new Usernotfoundexception(MessageReference.USER_ID_NOT_FOUND); // if userid not found throw exception
																					// user not found

		} else {
			Notemodel note = id.get();
			note.setArchive(!(note.isArchive()));
			repo.save(note);
			return new Response(200, "archive change", true);

		}
	}

	/**
	 * purpose create method for pin unpin user note and label
	 */
	@Override
	public Response pin(String token, String noteid) {
		String userid = tokenutility.getUserToken(token); // if token is invalid then throw exception invalid token
		if (userid.isEmpty()) {
			throw new Tokenexception(MessageReference.INVALID_TOKEN);
		}

		Optional<Notemodel> id = repo.findByIdAndUserid(noteid, userid);
		Notemodel note = id.get(); // if userid not found throw exception user not found
		if (id.isEmpty()) {
			throw new Usernotfoundexception(MessageReference.USER_ID_NOT_FOUND);

		} else {
			note.setPin(!(note.isPin())); // if pin change if ture to false or false to ture
			repo.save(note); // store in db
			return new Response(200, "pin change", true);

		}
	}

	/**
	 * purpose create method for trash store user note retrive and delete
	 */
	@Override
	public Response trash(String token, String noteid) {

		String userid = tokenutility.getUserToken(token); // if token is invalid then throw exception invalid token
		if (userid.isEmpty()) {
			throw new Tokenexception(MessageReference.INVALID_TOKEN);
		}
		Optional<Notemodel> id = repo.findByIdAndUserid(noteid, userid);
		Notemodel note = id.get();
		if (id.isEmpty()) {
			throw new Usernotfoundexception(MessageReference.USER_ID_NOT_FOUND); // if userid not found throw exception
																					// user not found

		} else {
			note.setTrash(!(note.isTrash()));
			repo.save(note);
			return new Response(200, "trash change", true);

		}
	}

	/**
	 * purpose create method for add reminder
	 */
	@Override
	public Response addReminder(Date date, String noteid, String token) {
		String userid = tokenutility.getUserToken(token);
		Optional<Notemodel> id = repo.findByIdAndUserid(noteid, userid);
		if (id.isEmpty()) {
			throw new Usernotfoundexception(MessageReference.NOTE_ID_NOT_FOUND); // if userid not found throw exception
																					// user not found

		}
		Notemodel note = id.get(); // get id in db

		note.setRemider(date); // set reminder
		repo.save(note);

		return new Response(200, MessageReference.REMINDER_SET__SUCCESSFULLY, true);
	}

	/**
	 * purpose create method for remove reminder
	 */
	@Override
	public Response removeReminder(String noteid, String token) {
		String userid = tokenutility.getUserToken(token);
		Optional<Notemodel> id = repo.findByIdAndUserid(noteid, userid);
		if (id.isEmpty()) {
			throw new Usernotfoundexception(MessageReference.NOTE_ID_NOT_FOUND); // if userid not found throw exception
																					// user not found

		}
		Notemodel note = id.get(); // get id in db

		note.setRemider(null); // remove reminder
		repo.save(note);

		return new Response(200, MessageReference.REMINDER_DELETE__SUCCESSFULLY, true);
	}

	

}
