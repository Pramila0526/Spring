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

import java.io.IOException;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.note.dto.Collabratordto;
import com.bridgelabz.note.dto.Notedto;
import com.bridgelabz.note.response.Response;
import com.bridgelabz.note.services.ElasticsearchserviceImp;
import com.bridgelabz.note.services.NoteserviceImp;

@RestController
//@RequestMapping("/")
public class Notecontroller {

	@Autowired
	NoteserviceImp noteServiceImp;

	@Autowired
	ElasticsearchserviceImp elasticsearchserviceImp;

	/**
	 * @param notedto create note for user
	 * @param token   user token
	 * @return if note is create return add note successfully
	 * @throws IOException 
	 */
	@PostMapping("/addNote")
	public ResponseEntity<Response> createNote(@Valid @RequestBody Notedto notedto, @RequestParam String token) throws IOException {

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

		return new ResponseEntity<Response>(noteServiceImp.deletePermanentNote(id), HttpStatus.OK);

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
	
	/**
	 * @param title    search perticular tilte in  elastic search
	 * @return         if title is found return  user note or not
	 
	 */
	
	@GetMapping("/searchbytitle")
	public ResponseEntity<Response> searchdByTitle( @RequestParam String title) throws Exception {
       
	
		return new ResponseEntity<Response>(elasticsearchserviceImp.searchByTitle(title), HttpStatus.OK);

	}
	/**
	 * @param description   search perticular description in  elastic search
	 * @return               if description is found return  user note or not
	 * @throws Exception
	 */
	@GetMapping("/searchbyDescription")
	public ResponseEntity<Response> searchdByDescription( @RequestParam String description) throws Exception {

		return new ResponseEntity<Response>(elasticsearchserviceImp.searchByDescription(description), HttpStatus.OK);
		

	}
	/**
	 * @param date    user provide date for stroing
	 * @param noteid  which note want to remider it
	 * @return        if reminder add return reminder add successfully or not
	 */
	@PutMapping("/addreminder")
	public ResponseEntity<Response> addReminder(@RequestHeader Date date,@RequestParam String noteid){
		System.out.println("controller");
		return new ResponseEntity<Response>(noteServiceImp.addReminder(date, noteid),HttpStatus.OK);
	}
	/**
	 * @param noteid    which note want delete a  reminder
	 * @return          if reminder remove return reminder delete successfully or not  
	 */
	@DeleteMapping("/removereminder")
	public ResponseEntity<Response> removeReminder(@RequestParam String noteid){
		
		return new ResponseEntity<Response>(noteServiceImp.removeReminder( noteid),HttpStatus.OK);
	}
	
	

}
