/******************************************************************************
 *  Compilation:    javac -d bin Notecontroller.java
 *  Execution:      java -cp bin com.bridgelabz.Noteserviceimp.createNote    notedto , token
 *                  java -cp bin com.bridgelabz.Noteserviceimp.deleteNote    id
 *                  java -cp bin com.bridgelabz.Noteserviceimp.searchNote    id
 *                  java -cp bin com.bridgelabz.Noteserviceimp.UpdateNote    notedto  id
 *                  java -cp bin com.bridgelabz.Noteserviceimp.showAllNote   
 *                  java -cp bin com.bridgelabz.Noteserviceimp.sortNoteByName   
 *                  java -cp bin com.bridgelabz.Noteserviceimp.sortNoteByDate    
 *                  java -cp bin com.bridgelabz.Noteserviceimp.addCollabrator collabratorDto    
 *               
 *  
 *  Purpose:       create controller for  user note
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since  19-11-2019
 *
 ******************************************************************************/
package com.bridgelabz.note.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.note.dto.Collabratordto;
import com.bridgelabz.note.dto.Notedto;

import com.bridgelabz.note.response.Response;
import com.bridgelabz.note.services.MessageReference;
import com.bridgelabz.note.services.NoteserviceImp;

@RestController
@RequestMapping("/")
public class Notecontroller {

	@Autowired
	NoteserviceImp noteServiceImp;

	/**
	 * @param notedto   create note for user 
	 * @param token     user token
	 * @return          if  note is create return  add note successfully
	 */
	@PostMapping("/addNote")
	public Response createNote(@RequestBody Notedto notedto,@RequestParam String token) {

		
		System.out.println("token:"+token);
		noteServiceImp.createNote(notedto,token);

		return new Response(200, "note add", MessageReference.NOTE_ADD_SUCCESSFULLY);

	}

	/**
	 * @param id     which note you want delete it
	 * @return       if note delete retrun delete sucessfully.
	 */
	@DeleteMapping("/deleteNote")
	public Response deleteNote(@RequestParam String id) {

		noteServiceImp.deleteNote(id);
		return new Response(200, "note add", MessageReference.NOTE_DELETE_SUCCESSFULLY);

	}

	/**
	 * @param id  search the perticular note in db through by id
	 * @return    if record is found retrun it.
	 */
	@GetMapping("/findNote")
	public Response findNote(@RequestParam String id) {

		return new Response(200, "note info", noteServiceImp.searchNote(id));
	}

	/**
	 * @return   return all note for user
	 */
	
	@GetMapping("/show")
	public Response showAllNote() {
		return new Response(200, "all note info", noteServiceImp.showAllNote());

	}

	/**
	 * @param notedto  user provide  to updaed detail to controller
	 * @param id        user want which note to update a details
	 * @return          if note it update  retrun update sucessfully.
	 */
	@PutMapping("/updateNote")
	public Response updateNote(@RequestBody Notedto notedto, @RequestParam String id) {
		
	
          
		  noteServiceImp.UpdateNote(notedto, id);
		return new Response(200, "Note update", MessageReference.NOTE_UPDATE_SUCCESSFULLY);
	}
	
	/**
	 * @return  return all sorted note by date
	 */
	@GetMapping("/sortnotebyname")
	public Response sortNoteByName() {
		
		return new Response(200, "Sort note by name",noteServiceImp.sortNoteByName() );
	}
	/**
	 * @return   return all sorted note by name
	 */
	@GetMapping("/sortnotebynote")
	public Response sortNoteByDate() {
		     System.out.println("in controller");
		
		return new Response(200, "Sort note by Date",noteServiceImp.sortNoteByDate() );
	}
	
	
	/**
	 * @param   collabratorDto provide  details which want to collabrate
	 * @return  if other user is collbrate retru  add collabrator succssfully
	 */
	@GetMapping("/addcollbrator")
	public  Response addCollbrator( @RequestBody Collabratordto collabratorDto) {
		
		return new Response(200, "add collbrator", noteServiceImp.addCollabrator(collabratorDto) );
	}
	
	 

}
