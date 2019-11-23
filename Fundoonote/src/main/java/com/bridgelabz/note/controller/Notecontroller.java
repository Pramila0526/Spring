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

import com.bridgelabz.note.dto.Notedto;

import com.bridgelabz.note.response.Response;
import com.bridgelabz.note.services.MessageReference;
import com.bridgelabz.note.services.NoteserviceImp;

@RestController
@RequestMapping("/")
public class Notecontroller {

	@Autowired
	NoteserviceImp noteServiceImp;

	@PostMapping("/addNote")
	public Response createNote(@RequestBody Notedto notedto,@RequestParam String token) {

		
		System.out.println("token:"+token);
		noteServiceImp.createNote(notedto,token);

		return new Response(200, "note add", MessageReference.NOTE_ADD_SUCCESSFULLY);

	}

	@DeleteMapping("/deleteNote")
	public Response deleteNote(@RequestParam String id) {

		noteServiceImp.deleteNote(id);
		return new Response(200, "note add", MessageReference.NOTE_DELETE_SUCCESSFULLY);

	}

	@GetMapping("/findNote")
	public Response findNote(@RequestParam String id) {

		return new Response(200, "note info", noteServiceImp.searchNote(id));
	}

	@GetMapping("/show")
	public Response showAllNote() {
		return new Response(200, "all note info", noteServiceImp.showAllNote());

	}

	@PutMapping("/updateNote")
	public Response updateNote(@RequestBody Notedto notedto, @RequestParam String id) {
		
	
          
		  noteServiceImp.UpdateNote(notedto, id);
		return new Response(200, "Note update", MessageReference.NOTE_UPDATE_SUCCESSFULLY);
	}
	@GetMapping("/sortnotebyname")
	public Response sortNoteByName() {
		
		return new Response(200, "Sort note by name",noteServiceImp.sortNoteByName() );
	}
	 

}
