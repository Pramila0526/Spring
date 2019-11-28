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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.bridgelabz.note.services.NoteserviceImp;

@RestController
@RequestMapping("/")
public class Notecontroller {

	@Autowired
	NoteserviceImp noteServiceImp;

	/**
	 * @param notedto create note for user
	 * @param token   user token
	 * @return if note is create return add note successfully
	 */
	@PostMapping("/addNote")
	public ResponseEntity<Response> createNote(@Valid @RequestBody Notedto notedto, @RequestParam String token) {

		System.out.println("token:" + token);
		noteServiceImp.createNote(notedto, token);
		return new ResponseEntity<Response>(noteServiceImp.createNote(notedto, token), HttpStatus.OK);

	}

	/**
	 * @param id which note you want delete it
	 * @return if note delete return delete successfully.
	 */
	@DeleteMapping("/deleteNote")
	public ResponseEntity<Response> deleteNote(@RequestParam String id) {

		return new ResponseEntity<Response>(noteServiceImp.deleteNote(id), HttpStatus.OK);

	}

	/**
	 * @param id search the particular note in db through by id
	 * @return if record is found return it.
	 */
	@GetMapping("/findNote")
	public ResponseEntity<Response> findNote(@Valid @RequestParam String id) {

		return new ResponseEntity<Response>(noteServiceImp.searchNote(id), HttpStatus.OK);

	}

	/**
	 * @return return all note for user
	 */

	@GetMapping("/shownote")
	public Response showAllNote() {

		return new Response(200, "show all note", noteServiceImp.showAllNote());

	}

	/**
	 * @param notedto user provide to updated detail to controller
	 * @param id      user want which note to update a details
	 * @return if note it update return update successfully.
	 */
	@PutMapping("/updateNote")
	public ResponseEntity<Response> updateNote(@Valid @RequestBody Notedto notedto, @RequestParam String id) {

		return new ResponseEntity<Response>(noteServiceImp.UpdateNote(notedto, id), HttpStatus.OK);

	}

	/**
	 * @return return all sorted note by date
	 */
	@GetMapping("/sortnotebyname")
	public ResponseEntity<Response> sortNoteByName() {

		return new ResponseEntity<Response>(noteServiceImp.sortNoteByName(), HttpStatus.OK);

	}

	/**
	 * @return return all sorted note by name
	 */
	@GetMapping("/sortnotebydate")
	public ResponseEntity<Response> sortNoteByDate() {

		return new ResponseEntity<Response>(noteServiceImp.sortNoteByDate(), HttpStatus.OK);

	}

	/**
	 * @param collabratorDto provide details which want to collaborate
	 * @return if other user is collaborate return add collabrator successfully
	 */
	@PutMapping("/addcollbrator")
	public ResponseEntity<Response> addCollbrator(@Valid @RequestBody Collabratordto collabratorDto) {

		return new ResponseEntity<Response>(noteServiceImp.addCollabrator(collabratorDto), HttpStatus.OK);

	}

}
